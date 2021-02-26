package com.ws.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 14:04
 */

@Getter
@Setter
public class AggregateLookup {

    private String from;

    private String localField;

    private String foreignField;

    private String as;

}
