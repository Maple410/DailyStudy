package com.ws.query;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 13:51
 */
@Getter
@Setter
public class AggregateQuery {

    private long pageSize = 10;

    private long page = 1;

    private AggregateGroup group;

    private List<AggregateLookup> lookups;

    private AggregateMatch match;

    private LinkedHashMap<String, Integer> sort;
}
