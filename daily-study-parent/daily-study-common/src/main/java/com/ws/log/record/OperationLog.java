package com.ws.log.record;

import java.lang.annotation.*;

/**
 * @Author: wangshuo
 * @Date: 2021/10/8 11:16
 */

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OperationLog {

    String message();

    String operation();
}
