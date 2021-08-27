package com.ws.config;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @Author: wangshuo
 * @Date: 2021/8/12 11:16
 */
@Configuration
public class EventBusConfigBean {


    @Bean
    @Scope("singleton")
    public AsyncEventBus asyncEventBus(){
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
