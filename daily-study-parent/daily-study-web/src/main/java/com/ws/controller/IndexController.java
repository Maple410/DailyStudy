package com.ws.controller;

import com.ws.domains.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2020/12/11 10:39
 */

@RestController
@RequestMapping("/api/index")
public class IndexController {


    /**
     * 测试demo
     * @param demo
     * @return
     */
    @GetMapping("/demo")
    public AjaxResult index(@RequestParam String demo) {
        return new AjaxResult(demo).success();
    }
}
