package com.ws.retry.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ws.retry.IOutSourceService;
import com.ws.retry.IRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2023/8/25 11:52
 * https://juejin.cn/post/7234107489390116925#comment
 *
 *  （1）开启 Retry 功能，需在启动类中使用 @EnableRetry 注解
 *  （2）需要在重试的代码中加入重试注解 @Retryable
 *  （3）重试配置
 *     value 与 include 含义相同，表示可重试的异常类型。默认为空，如果同时 exclude 也为空则会重试所有异常。
 *     exclude 不可重试的异常类型。默认为空（如果 include 也为为空，将重试所有异常）。如果 include 为空但 exclude 不为空，则重试非 exclude 中的异常
 *     maxAttempts 最大重试次数，默认为 3
 *     maxAttemptsExpression 最大尝试次数的表达式，表达式一旦设置了值，则会覆盖 maxAttempts 的值，maxAttemptsExpression 可以读取 application.yml 配置文件里的数据，也可以通过 SpEL 表达式计算对应的值
 *     exceptionExpression  异常处理表达式，ExpressionRetryPolicy 中使用，执行完父类的 canRetry 之后，需要校验 exceptionExpression 的值，为 true 则可以重试
 *  （4） 兜底方法 @Recover
 *      当 @Retryable 方法重试失败之后，最后就会调用 @Recover 方法。用于 @Retryable 失败时的“兜底”处理方法。
 *      @Recover 的方法必须要与 @Retryable 注解的方法保持一致，第一入参为要重试的异常，其他参数与 @Retryable 保持一致，返回值也要一样，否则无法执行！
 *  （5）熔断模式 @CircuitBreaker
 *      指在具体的重试机制下失败后打开断路器，过了一段时间，断路器进入半开状态，允许一个进入重试，若失败再次进入断路器，成功则关闭断路器，
 *      注解为 @CircuitBreaker ,具体包括熔断打开时间、重置过期时间
 *  （6）重试策略
 *    SimpleRetryPolicy 默认最多重试 3 次
 *    TimeoutRetryPolicy 默认在 1 秒内失败都会重试
 *    ExpressionRetryPolicy 符合表达式就会重试
 *    CircuitBreakerRetryPolicy 增加了熔断的机制，如果不在熔断状态，则允许重试
 *    CompositeRetryPolicy 可以组合多个重试策略
 *    NeverRetryPolicy 从不重试（也是一种重试策略哈）
 *    AlwaysRetryPolicy 总是重试
 *   （7）退避策略
 *      退避策略退避是指怎么去做下一次的重试，在这里其实就是等待多长时间。通过 @Backoff 注解实现，那么我们首先看一下@Backoff 的参数
 */
@Service
@Slf4j
public class RetryServiceImpl implements IRetryService {

    @Autowired
    private IOutSourceService outSourceService;

    @Override
    @Retryable(value = RuntimeException.class,maxAttempts = 3)//只会在抛出RuntimeException 的时候才会重试
    public List<Integer> getOutSourceResult(String data){

        log.info("trigger timestamp:{}",System.currentTimeMillis()/1000);
        List<Integer> resultList = outSourceService.getResult();

        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList;
        }
        log.error("getOutSourceResult error, data:{}", data);

        return null;

    }
}
