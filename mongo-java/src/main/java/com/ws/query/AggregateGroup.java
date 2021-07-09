package com.ws.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 13:59
 */
@Getter
@Setter
public class AggregateGroup {

    private List<String> fields;

    private List<Indicator> indicators;
}
