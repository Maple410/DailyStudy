package com.ws.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.ws.domains.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2021/8/12 16:59
 */

@RestController
@RequestMapping("/api/eventBus")
public class EventBusController {


    @Autowired
    private AsyncEventBus asyncEventBus;


    @GetMapping("/demo")
    public AjaxResult demo(@RequestParam String demo) {
        for(int i=0;i<= 500;i++){
            asyncEventBus.post(demo + ":" +i);
        }
        return new AjaxResult().success();
    }
}
