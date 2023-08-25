package com.ws.retry.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ws.retry.IOutSourceService;
import com.ws.retry.IRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wangshuo
 * @Date: 2023/8/25 11:52
 */
@Service
@Slf4j
public class RetryServiceImpl implements IRetryService {

    @Autowired
    private IOutSourceService outSourceService;

    @Override
    @Retryable(value = RuntimeException.class,maxAttempts = 3)
    public List<Integer> getOutSourceResult(String data){

        log.info("trigger timestamp:{}",System.currentTimeMillis()/1000);
        List<Integer> resultList = outSourceService.getResult();

        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList;
        }
        log.error("getOutSourceResult error, data:{}", data);

        return null;

    }
}
