package com.ws.aspect;

import com.ws.annotation.Submit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: wangshuo
 * @Date: 2021/12/23 9:46
 */
@Component
@Aspect
public class SubmitAspect {


    @Around("execution(public * *(..)) && @annotation(com.ws.annotation.Submit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Submit submit = method.getAnnotation(Submit.class);

        //引入 redis缓存 来防止重复提交 在需要防止重复提交的地方 引入注解 进行判定

        return pjp.proceed();
    }

    private String getCacheKey(Submit submit, Object[] args) {
        String prefix = submit.prefix();
        return prefix + args[0];
    }
}
