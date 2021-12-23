package com.ws.annotation;

import java.lang.annotation.*;

/**
 * @Author: wangshuo
 * @Date: 2021/12/23 9:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Submit {

    String prefix() default "prefix:";
}
