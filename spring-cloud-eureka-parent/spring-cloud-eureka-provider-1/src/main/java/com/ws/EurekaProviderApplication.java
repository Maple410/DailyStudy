package com.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2022/12/7 15:28
 */

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/provider")
    public String home() {
        return "Hello world,port:" + port;
    }
}
