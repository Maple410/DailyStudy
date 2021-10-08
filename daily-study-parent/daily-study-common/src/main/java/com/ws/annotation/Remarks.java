package com.ws.annotation;

import java.lang.annotation.*;

/**
 * @Author: wangshuo
 * @Date: 2021/9/28 15:18
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Remarks {
    String name() default "";

    String dateFormat() default "yyyy-MM-dd";

    boolean dateTimeFlag() default false;

}
