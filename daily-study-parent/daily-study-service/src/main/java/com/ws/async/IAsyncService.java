package com.ws.async;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: wangshuo
 * @Date: 2021/12/13 15:58
 */
public interface IAsyncService {

    String doSomeThing(String message);

    CompletableFuture<String> doSomeThing1(String message) throws InterruptedException;

    CompletableFuture<String> doSomeThing2(String message) throws InterruptedException;

    CompletableFuture<String> doSomeThing3(String message) throws InterruptedException;
}
