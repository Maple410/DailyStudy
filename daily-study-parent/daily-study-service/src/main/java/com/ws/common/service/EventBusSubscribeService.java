package com.ws.common.service;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: wangshuo
 * @Date: 2021/8/12 11:21
 */
@Service
@Slf4j
public class EventBusSubscribeService {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void register() {
        asyncEventBus.register(this);
    }

    @Subscribe
    @AllowConcurrentEvents
    public void sendNotice(String message){
        System.out.println("哈哈哈".concat(message));
    }

    @Subscribe
    @AllowConcurrentEvents
    public void sendAll(Integer num){
        System.out.println("数字会执行嘛".concat(num.toString()));
    }

}
