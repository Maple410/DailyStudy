package com.ws.config;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: wangshuo
 * @Date: 2021/8/12 16:57
 */

@Service
@Slf4j
public class EventBusSubscribeService {


    @Autowired
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void register(){
        asyncEventBus.register(this);
    }

    @Subscribe
    @AllowConcurrentEvents
     public void doSomeThing(String demo){
        log.info("成功: ".concat(demo));
        System.out.println(demo);

     }

}
