package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wangshuo
 * @Date: 2021/2/19 16:15
 */

@SpringBootApplication
@EnableFeignClients     //这是新增的注解 表示开启feign
@EnableEurekaClient
public class EurekaFeignClient {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignClient.class,args);
    }
}
