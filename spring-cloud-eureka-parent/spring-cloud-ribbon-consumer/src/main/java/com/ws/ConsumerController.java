package com.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: wangshuo
 * @Date: 2022/12/7 15:49
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/hello")
    public String hello() {
        return restTemplate.getForEntity("http://eureka-provider/provider/", String.class).getBody();
    }
}
