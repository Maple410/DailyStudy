package com.ws;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: wangshuo
 * @Date: 2022/12/7 16:11
 */
@FeignClient(value = "eureka-provider",fallbackFactory = HystrixClientFallbackFactory.class )
public interface HomeClient {

    @GetMapping("/provider")
    String consumer();
}
