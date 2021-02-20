package com.ws.controller;

import com.ws.EurekaFeignClient;
import com.ws.service.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2021/2/20 14:59
 */
@RestController
public class IndexController {

    @Autowired
    private EurekaClientFeign eurekaClientFeign;


    @GetMapping("/test")
    public String getName(){
        return eurekaClientFeign.getMessageFromClient("yzf");
    }
}
