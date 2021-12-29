package com.ws.controller;

import com.dtflys.forest.annotation.Get;
import com.ws.domains.AjaxResult;
import com.ws.forest.AmapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2021/12/24 17:43
 */

@RestController
@RequestMapping("/daily/forest")
public class ForestController {


    @Autowired
    private AmapClient amapClient;


    @GetMapping("/amap")
    public AjaxResult amap() {
        return new AjaxResult(amapClient.getLocation("121.475078", "31.223577")).success();
    }

}
