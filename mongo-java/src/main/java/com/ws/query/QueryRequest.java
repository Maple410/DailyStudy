package com.ws.query;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 15:26
 */
@Getter
@Setter
public class QueryRequest {

    private Integer pageSize;

    private Integer pageIndex;

    private List<OrderField> orders = new ArrayList<>();

    private List<Condition> conditions = new ArrayList<>();


}
