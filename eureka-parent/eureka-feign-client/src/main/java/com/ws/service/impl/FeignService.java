package com.ws.service.impl;

import com.ws.service.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangshuo
 * @Date: 2021/2/19 16:25
 */
@Service
public class FeignService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;

    public String get(String name){
        return eurekaClientFeign.getMessageFromClient(name);
    }
}
