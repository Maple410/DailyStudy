package com.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2022/12/7 16:12
 */
@RestController
public class ConsumerController {

    @Autowired
    private HomeClient homeClient;

    @GetMapping(value = "/hello")
    public String hello(){
        return homeClient.consumer();
    }
}
