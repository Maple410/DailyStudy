package com.ws.query;

import com.google.common.collect.Lists;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 14:18
 */
public class AbstractMongoDbService<T extends Serializable> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractMongoDbService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public Document insert(Document doc, String collectionName) {
        mongoTemplate.insert(doc, collectionName);
        logger.info("collectionName = {},doc = {}", collectionName, doc);
        return doc;
    }

    public void batchInsert(List<Document> docs, String collectionName) {
        mongoTemplate.insert(docs, collectionName);
        logger.info("collectionName = {},batchInsert success", collectionName);
    }

    public Long update(final Document doc, String collectionName) {
        Query query = Query.query(Criteria.where("_id").is((T) doc.get("_id")));
        final Update update = new Update();
        doc.keySet().forEach(item -> {
            if (item.equals("_id")) {
                return;
            }
            update.set(item, doc.get(item));
        });
        UpdateResult result = mongoTemplate.upsert(query, update, collectionName);
        return result.getModifiedCount();
    }


    public Document findById(T id, String collectionName) {
        return mongoTemplate.findById(id, Document.class, collectionName);
    }


    public PageResult<Map> page(QueryRequest queryRequest, String collectionName) {
        Query query = QueryUtil.constructQuery(queryRequest);
        List<Map> resultList = mongoTemplate.find(query, Map.class, collectionName);
        Long total = mongoTemplate.count(query.skip(0L).limit(Integer.MAX_VALUE), Map.class, collectionName);
        PageResult result = new PageResult(total.intValue(), queryRequest.getPageIndex(), queryRequest.getPageSize(), resultList);
        return result;
    }

    public List<Map> list(QueryRequest queryRequest, String collectionName) {
        Query query = QueryUtil.constructQuery(queryRequest);
        return mongoTemplate.find(query, Map.class, collectionName);
    }

    public List<Map> aggregate(AggregateQuery aggregateQuery, Fields fields, String collectionName) {
        Criteria criteria = QueryUtil.getCriteria(aggregateQuery.getMatch());
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria), Aggregation.group(fields).count().as("cnt"));
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, collectionName, Map.class);
        return results.getMappedResults();
    }

    private List<Map> aggregate(AggregateQuery aggregateQuery, String collectionName) {

        List<AggregationOperation> operationList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(aggregateQuery.getLookups())) {
            aggregateQuery.getLookups().stream().forEach(item -> {
                LookupOperation lookupOperation = Aggregation.lookup(item.getFrom(), item.getLocalField(), item.getForeignField(), item.getAs());
                operationList.add(lookupOperation);
                operationList.add(Aggregation.unwind(item.getAs()));
            });
        }

        if (Objects.nonNull(aggregateQuery.getMatch())) {
            Criteria criteria = QueryUtil.getCriteria(aggregateQuery.getMatch());
            operationList.add(Aggregation.match(criteria));
        }

        if (Objects.nonNull(aggregateQuery.getGroup())) {
            AggregationOperation group = QueryUtil.createAggregationOperation(aggregateQuery.getGroup());
            operationList.add(group);
        }

        if (CollectionUtils.isEmpty(aggregateQuery.getSort())) {
            Sort sort = QueryUtil.buildSort(aggregateQuery.getSort());
            operationList.add(Aggregation.sort(sort));
        }

        SkipOperation skip = Aggregation.skip((aggregateQuery.getPage() - 1) * aggregateQuery.getPageSize());
        operationList.add(skip);

        AggregationOperation limit = Aggregation.limit(aggregateQuery.getPageSize());
        operationList.add(limit);
        Aggregation aggregation = Aggregation.newAggregation(operationList);
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, collectionName, Map.class);
        return results.getMappedResults();

    }

    public long aggregateCount(AggregateQuery aggregateQuery, String collectionName) {

        List<AggregationOperation> aggregationOperationList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(aggregateQuery.getLookups())) {
            aggregateQuery.getLookups().forEach(item -> {
                LookupOperation lookupOperation = Aggregation.lookup(item.getFrom(), item.getLocalField(), item.getForeignField(), item.getAs());
                aggregationOperationList.add(lookupOperation);
                aggregationOperationList.add(Aggregation.unwind(item.getAs()));
            });
        }

        //过滤条件
        if (aggregateQuery.getMatch() != null) {
            Criteria criteria = QueryUtil.getCriteria(aggregateQuery.getMatch());
            aggregationOperationList.add(Aggregation.match(criteria));
        }

        AggregationOperation group = Aggregation.group().count().as("count");
        aggregationOperationList.add(group);

        Aggregation aggregation = Aggregation.newAggregation(aggregationOperationList);
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, collectionName, Map.class);
        if (!CollectionUtils.isEmpty(results.getMappedResults())) {
            List<Map> mappedResults = results.getMappedResults();
            return Long.valueOf(mappedResults.get(0).get("count").toString());
        }

        return 0;
    }

    public PageResult<Map> aggregatePage(AggregateQuery aggregateQuery, String collectionName) {
        List<Map> maps = aggregate(aggregateQuery, collectionName);
        Long total = aggregateCount(aggregateQuery, collectionName);

        PageResult pageResult = new PageResult(
                total.intValue(),
                (int) aggregateQuery.getPage(),
                (int) aggregateQuery.getPageSize(), maps);
        return pageResult;
    }


}
