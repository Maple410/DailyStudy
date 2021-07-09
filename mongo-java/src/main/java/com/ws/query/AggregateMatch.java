package com.ws.query;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 14:11
 */
@Getter
@Setter
public class AggregateMatch {

    private String pattern;

    List<MatchQuery> matchQueries;


}
