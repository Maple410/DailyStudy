package com.ws.config;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author: wangshuo
 * @Date: 2021/8/12 14:12
 *
 * https://blog.csdn.net/qq_38345296/article/details/100539989
 *
 * https://www.appblog.cn/2021/01/24/Spring%20Boot%E9%9B%86%E6%88%90EventBus%EF%BC%88Guava%E6%96%B9%E5%BC%8F%EF%BC%89/
 */
@Configuration
public class EventBusConfigBean {

    @Bean
    @Scope("singleton")
    public AsyncEventBus asyncEventBus() {
        final ThreadPoolTaskExecutor executor = executor();
        return new AsyncEventBus(executor);
    }

    @Bean
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        return executor;
    }


}
