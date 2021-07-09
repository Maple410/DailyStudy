package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2021/2/19 16:04
 */

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApp.class,args);
    }

    @GetMapping("/list")
    public String list(@RequestParam("name") String name){
        return "Hello ===" + name +",this is your world";
    }
}
