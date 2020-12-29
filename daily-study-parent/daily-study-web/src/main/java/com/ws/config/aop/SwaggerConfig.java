package com.ws.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: wangshuo
 * @Date: 2020/12/29 16:58
 * 接入Swagger2，用于生成API接口文档，API文档请求地址：http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        //Docket是Swagger重要的构造器，为swagger配置提供默认值和方法
        //DocumentationType.SWAGGER_2表示使用swagger版本2.0
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                //传入自定义的API描述信息
                .apiInfo(apiInfo())
                //返回一个api选择构建器
                .select()
                //指定扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.ws.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("ws-DailyStudy")
                //描述
                .description("日常学习接口V1.0")
                //版本描述
                .version("v1.0")
                .build();
    }
}
