package com.ws.log.record;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: wangshuo
 * @Date: 2021/10/8 11:20
 * 参考 ：https://blog.csdn.net/qq_33576276/article/details/88786090   https://blog.csdn.net/qq_33576276/article/details/88823292
 */
@Aspect
@Component
@Slf4j
public class OperationAspect {


    @Pointcut("@annotation(com.ws.log.record.OperationLog)")
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public Object saveSysLog(ProceedingJoinPoint proceedingJoinPoint) throws Exception {


        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        OperationLog myLog = method.getAnnotation(OperationLog.class);
        if (null != myLog) {
            String message = myLog.message();
            String description = executeTemplate(message, proceedingJoinPoint);
            sysLog.setMessage(description);
        }

        //获取请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        sysLog.setCreateDate(new Date());

        //获取用户名
        //获取用户ip地址
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        sysLog.setIp(request.getRemoteAddr());

        //开始调用时间
        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Long time = System.currentTimeMillis() - start;
        sysLog.setTotalTime(time);

        System.out.println("打印日志信息：".concat(sysLog.toString()));

        try {
            Object result = proceedingJoinPoint.proceed();
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    private String executeTemplate(String message, ProceedingJoinPoint joinPoint) throws Exception {

        ExpressionParser parser = new SpelExpressionParser();

        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        String[] params = discoverer.getParameterNames(method);

        Object[] args = joinPoint.getArgs();

        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }
        return parser.parseExpression(message, new TemplateParserContext()).getValue(context, String.class);
    }
}
