package com.ws.controller;

import com.ws.service.impl.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2021/2/19 16:27
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping("/list")
    public String get(@RequestParam(defaultValue = "sjj", required = false) String name) {
        return feignService.get(name);
    }

    @GetMapping("listPage")
    public Object list(@RequestParam String weekDate, @RequestParam(required = false) String userId) {
        return feignService.list(weekDate, userId);
    }
}
