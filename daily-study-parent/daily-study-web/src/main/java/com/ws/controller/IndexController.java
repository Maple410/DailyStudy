package com.ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @Author: wangshuo
 * @Date: 2020/12/11 10:39
 */

@RestController
public class IndexController {


    @GetMapping("/index")
    public String index() {
        return "This is a demo!!!";
    }
}
