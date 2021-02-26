package com.ws.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 17:13
 */
@Getter
@Setter
public class MatchQuery {
    private String field;

    private String operator;

    private Object value;

    private Object[] arrayValue;

}
