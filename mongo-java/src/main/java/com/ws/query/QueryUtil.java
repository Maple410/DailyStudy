package com.ws.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 15:39
 */
public class QueryUtil {

    private static final Logger logger = LoggerFactory.getLogger(QueryUtil.class);

    public static Query constructQuery(QueryRequest request) {
        Criteria criteria = getCriteria(request);
        Query query = new Query(criteria);
        if (request.getPageSize() > 0) {
            Pageable pageable = PageRequest.of(request.getPageIndex() - 1, request.getPageSize());
            query.with(pageable);
        }
        List<Sort.Order> listOrder = getSortList(request);
        if (!CollectionUtils.isEmpty(listOrder)) {
            query.with(Sort.by(listOrder));
        }
        logger.info("find query:{}", query);
        return query;
    }


    private static Criteria getCriteria(QueryRequest queryRequest) {
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>(32);

        if (!CollectionUtils.isEmpty(queryRequest.getConditions())) {
            queryRequest.getConditions().stream().forEach(item -> {
                criteriaList.add(getMongoCriteria(item));
            });
        }
        if (CollectionUtils.isEmpty(criteriaList)) {
            return criteria;
        }
        return criteria.andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));

    }


    public static Criteria getCriteria(AggregateMatch aggregateMatch) {
        List<Criteria> criteriaList = new ArrayList<>();

        List<MatchQuery> matchQueries = aggregateMatch.getMatchQueries();
        matchQueries.stream().forEach(item -> {
            if (item.getField().equals("field")) {
                Condition condition = new Condition();
                BeanUtils.copyProperties(item, condition);
                condition.setOperator(ConditionOperator.valueOf(item.getOperator()));
                Criteria criteria = getMongoCriteria(condition);
                criteriaList.add(criteria);
            } else {
                //Criteria criteria = getMongoCriteria()
            }
        });

        if (aggregateMatch.getPattern().equals("and")) {
            return new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        } else {
            return new Criteria().orOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        }
    }

    /**
     * 构造查询条件
     *
     * @param condition
     * @return
     */
    private static Criteria getMongoCriteria(Condition condition) {
        Criteria criteria = Criteria.where(condition.getField());
        switch (condition.getOperator()) {
            case NE:
                criteria.ne(condition.getValue());
                break;
            case LTE:
                criteria.lte(condition.getValue());
                break;
            case LT:
                criteria.lt(condition.getValue());
                break;
            case GTE:
                criteria.gte(condition.getValue());
                break;
            case GT:
                criteria.gt(condition.getValue());
                break;
            case EQ:
                criteria.is(condition.getValue());
                break;
            case IN:
                criteria.in(condition.getArray());
                break;
            case NIN:
                criteria.nin(condition.getArray());
                break;
            case BETWEEN:
                criteria.gte(condition.getValue()).lte(condition.getSecondValue());
                break;
            case LIKE:
                Pattern pattern = Pattern.compile("^.*" + condition.getValue() + ".*$", Pattern.CASE_INSENSITIVE);
                criteria.regex(pattern);
                break;
        }
        return criteria;
    }

    private static List<Sort.Order> getSortList(QueryRequest queryRequest) {
        List<Sort.Order> listOrder = new ArrayList<>();
        queryRequest.getOrders().stream().forEach(item -> {
            Sort.Order order = null;
            if (item.isAsc()) {
                order = Sort.Order.asc(item.getColumn());
            } else {
                order = Sort.Order.desc(item.getColumn());
            }
            listOrder.add(order);
        });
        return listOrder;
    }

    public static Sort buildSort(LinkedHashMap<String, Integer> sorts) {

        List<Sort.Order> orders = Lists.newArrayList();
        sorts.forEach((field, direction) -> {
            if (direction == 1) {
                orders.add(Sort.Order.asc(field));
            } else {
                orders.add(Sort.Order.desc(field));
            }
        });

        return Sort.by(orders);
    }


    public static AggregationOperation createAggregationOperation(AggregateGroup group) {

        List list = group.getFields();
        String[] column = new String[list.size()];
        list.toArray(column);
        final GroupOperation[] groupOperation = {Aggregation.group(column)};
        group.getIndicators().forEach(indicator -> {
            String operator = indicator.getOperator();
            groupOperation[0] = groupOperation(groupOperation[0], operator, indicator.getField()).as(indicator.getAlias());
        });

        return groupOperation[0];
    }

    /**
     * 聚会统计方式
     *
     * @param groupOperation
     * @param reference
     * @return
     */
    private static GroupOperation.GroupOperationBuilder groupOperation(GroupOperation groupOperation, String operator, String reference) {

        GroupOperation.GroupOperationBuilder builder = null;
        switch (operator) {
            case "max":
                builder = groupOperation.max(reference);
                break;
            case "min":
                builder = groupOperation.min(reference);
                break;
            case "count":
                builder = groupOperation.count();
                break;
            case "avg":
                builder = groupOperation.avg(reference);
                break;
            case "first":
                builder = groupOperation.first(reference);
                break;
            case "last":
                builder = groupOperation.last(reference);
                break;
            case "sum":
                builder = groupOperation.sum(reference);
                break;
        }
        return builder;
    }

}
