package com.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: wangshuo
 * @Date: 2020/12/11 10:47
 */

@SpringBootApplication
@MapperScan(value = "com.ws.service.mapper")
public class RootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
