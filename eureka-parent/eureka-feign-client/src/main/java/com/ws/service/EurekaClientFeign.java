package com.ws.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wangshuo
 * @Date: 2021/2/19 16:22
 */
@Component
@FeignClient(value = "eureka-client")
public interface EurekaClientFeign {

    @GetMapping("/list")    //客户端的方法地址
    String getMessageFromClient(@RequestParam(value = "name")String name);

    @GetMapping("/api/workHour/query/list")
    Object page(@RequestParam String weekDate, @RequestParam(required = false) String userId);
}
