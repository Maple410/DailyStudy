package com.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: wangshuo
 * @Date: 2021/2/24 18:45
 */
@SpringBootApplication
@MapperScan(value = "com.ws.service.mapper")
public class SeleniumApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeleniumApplication.class,args);
    }
}
