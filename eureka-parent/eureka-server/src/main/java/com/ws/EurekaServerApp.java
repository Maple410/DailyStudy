package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: wangshuo
 * @Date: 2021/2/19 15:57
 *
 * 参考链接：https://blog.csdn.net/weixin_43928997/article/details/90668007
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
