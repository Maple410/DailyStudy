package com.ws.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ws.domains.AjaxResult;
import com.ws.retry.IRetryService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2023/8/25 11:53
 * 重试框架：spring-retry
 * https://juejin.cn/post/7177616402180735013
 */
@RestController
@RequestMapping("/api/retry")
@Slf4j
public class RetryController {


    @Autowired
    private IRetryService retryService;


    @GetMapping("retryable")
    public AjaxResult manuallyRetry2() {
        try {
            List<Integer> resultList = retryService.getOutSourceResult("aaaa");
            if (CollectionUtils.isNotEmpty(resultList)) {
                return new AjaxResult().success();
            }
        } catch (Exception e) {
            log.error("retryable final exception", e);
        }
        return new AjaxResult().fail();
    }

}
