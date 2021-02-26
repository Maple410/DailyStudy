package com.ws.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 15:28
 */
@Getter
@Setter
public class OrderField implements Serializable {

    private String column;

    private boolean asc = true;
}
