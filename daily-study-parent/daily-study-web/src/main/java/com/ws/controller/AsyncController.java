package com.ws.controller;

import com.ws.async.IAsyncService;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: wangshuo
 * @Date: 2021/12/13 15:56
 *
 *  参考掘金：https://juejin.cn/post/6844904173977206797
 */
@RestController
@RequestMapping("/api/async")
@Slf4j
public class AsyncController {

    @Autowired
    private IAsyncService asyncService;


    @GetMapping("/open/something")
    public String something() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            asyncService.doSomeThing("index = " + i);
        }
        return "success";
    }

    @SneakyThrows
    @ApiOperation("异步 有返回值")
    @GetMapping("/open/somethings")
    public String somethings() {
        CompletableFuture<String> createOrder = asyncService.doSomeThing1("create order");
        CompletableFuture<String> reduceAccount = asyncService.doSomeThing2("reduce account");
        CompletableFuture<String> saveLog = asyncService.doSomeThing3("save log");

        // 等待所有任务都执行完
        CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
        // 获取每个任务的返回结果
        String result = createOrder.get() + reduceAccount.get() + saveLog.get();
        return result;
    }

}
