package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wangshuo
 * @Date: 2021/2/20 14:58
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ws.service")
public class EurekaConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConnectApplication.class, args);
    }
}
