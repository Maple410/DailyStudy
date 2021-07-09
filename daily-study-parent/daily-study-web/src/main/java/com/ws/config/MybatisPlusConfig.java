package com.ws.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ws.interceptor.SelectMybatisInterceptor;
import com.ws.interceptor.UpdateMybatisInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:15
 * mybatis-plus分页查询配置类，返回一个分页拦截器
 */

@Configuration
@ConditionalOnClass(value = PaginationInterceptor.class)
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }


    @Bean
    public SelectMybatisInterceptor selectAppIdMybatisInterceptor() {
        return new SelectMybatisInterceptor();
    }

    @Bean
    public UpdateMybatisInterceptor updateMybatisInterceptor() {
        return new UpdateMybatisInterceptor();
    }
}
