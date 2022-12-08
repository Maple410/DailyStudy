package com.ws;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: wangshuo
 * @Date: 2022/12/8 16:12
 */
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<HomeClient> {

    @Override
    public HomeClient create(Throwable throwable) {
        return () -> "feign + hystrix ,提供者服务挂了";
    }
}
