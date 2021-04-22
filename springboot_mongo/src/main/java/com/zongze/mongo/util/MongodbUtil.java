package com.zongze.mongo.util;

import com.zongze.mongo.domain.PageResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Date 2021/3/25 11:47
 * @Created by xiezz
 */
public class MongodbUtil {

    private static MongoTemplate mongoTemplate;

    public static void setMongoTemplate(MongoTemplate mongoTemplate) {
        MongodbUtil.mongoTemplate = mongoTemplate;
    }


    /**
     * 插入一条
     *
     * @param tableName table名称
     * @param object    存储对象
     * @return void
     */
    public static <T> T insertOne(String tableName, T object) {
        return mongoTemplate.insert(object, tableName);
    }


    /**
     * 批量插入
     *
     * @param tableName table名称
     * @param object    存储对象
     * @return void
     */
    public static <T> Collection<T> insertBatch(String tableName, Collection<T> object) {
        return mongoTemplate.insert(object, tableName);
    }


    /**
     * 根据条件按查询,查询单个
     *
     * @param table
     * @param classType
     * @return java.util.List<T>
     */
    public static <T> T queryOne(String table, Class<T> classType, MongoCondition mongoCondition) {
        Query query = new Query();
        if (!CollectionUtils.isEmpty(mongoCondition.getConditions())) {
            mongoCondition.getConditions().entrySet().stream().forEach(entry -> query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue())));
        }
        return mongoTemplate.findOne(query, classType, table);
    }


    /**
     * 根据条件按查询，查询所有
     *
     * @param table
     * @param classType
     * @return java.util.List<T>
     */
    public static <T> List<T> queryList(String table, Class<T> classType, MongoCondition mongoCondition) {
        Query query = new Query();
        setCondition(query, mongoCondition);
        return mongoTemplate.find(query, classType, table);
    }


    /**
     * 根据条件按查询，查询所有
     *
     * @param table
     * @param classType
     * @return PageResult<T>
     */
    public static <T> PageResult<T> queryPage(String table, Class<T> classType, MongoCondition mongoCondition) {
        PageResult<T> pageResult = new PageResult<>();
        Query query = new Query();
        setCondition(query, mongoCondition);
        long count = mongoTemplate.count(query, table);
        pageResult.setTotalNum(count);
        query.with(PageRequest.of(mongoCondition.getPageNum()-1, mongoCondition.getPageSize()));
        pageResult.setData(mongoTemplate.find(query, classType, table));
        pageResult.setTotalPage((int)Math.ceil((float)count / mongoCondition.getPageSize()));
        return pageResult;
    }



    /**
     * 设置查询条件
     * @param query
     * @param mongoCondition
     * @return void
     */
    private static void setCondition(Query query, MongoCondition mongoCondition) {
        if (!CollectionUtils.isEmpty(mongoCondition.getConditions())) {
            mongoCondition.getConditions().entrySet().stream().forEach(entry -> query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue())));
        }
        if (!CollectionUtils.isEmpty(mongoCondition.getOrderConditions())) {
            Map<String, MongoCondition.OrderModel> orderConditions = mongoCondition.getOrderConditions();
            List<Sort.Order> orders = new ArrayList<>(orderConditions.size());
            orderConditions.entrySet().stream().forEach(entry -> {
                if (MongoCondition.OrderModel.ASC.equals(entry.getValue())) {
                    orders.add(Sort.Order.asc(entry.getKey()));
                } else {
                    orders.add(Sort.Order.desc(entry.getKey()));
                }
            });
            query.with(Sort.by(orders));
        }
    }


}
