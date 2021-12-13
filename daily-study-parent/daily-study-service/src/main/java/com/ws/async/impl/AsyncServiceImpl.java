package com.ws.async.impl;

import com.ws.async.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: wangshuo
 * @Date: 2021/12/13 15:59
 */
@Service
@Slf4j
public class AsyncServiceImpl implements IAsyncService {

    @Override
    @Async("doSomethingExecutor")
    public String doSomeThing(String message) {
        log.info("do something, message={}", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("do something error: ", e);
        }
        return message;
    }


    @Override
    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomeThing1(String message) throws InterruptedException {
        log.info("do something1: {}", message);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("do something1: " + message);

    }

    @Override
    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomeThing2(String message) throws InterruptedException {
        log.info("do something2: {}", message);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("; do something2: " + message);
    }

    @Override
    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomeThing3(String message) throws InterruptedException {
        log.info("do something3: {}", message);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("; do something3: " + message);
    }
}
