package com.zongze.mongo.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zongze.mongo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import sun.management.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/16 10:24
 * @Created by xzz
 */
@Service
public class MongoCollectionTestServiceImpl implements MongoCollectionTestService {


    @Autowired
    private MongoTemplate mongoTemplate;

    private final String TABLE_NAME = "tb_user";

    private static final Logger logger = LoggerFactory.getLogger(MongoCollectionTestServiceImpl.class);

    private static List<User> users =  new ArrayList<User>(){{
        add(new User("张无忌", 18));
        add(new User("张无忌", 20));
        add(new User("张无忌", 25));
        add(new User("张三", 25));
        add(new User("李四", 25));
    }};

    @Override
    public User save(User o) {
        return mongoTemplate.save(o, TABLE_NAME);
    }

    @Override
    public List<User> batchSave() {
        logger.info("users size is {}",users.size());
        return (List<User>) mongoTemplate.insert(users, TABLE_NAME);
    }

    @Override
    public User findOne(String name, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class, TABLE_NAME);
    }

    @Override
    public List<User> findAll(String name, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name)); //条件
        query.with(Sort.by("age").descending()); //单字段排序
//        query.with(Sort.by(Sort.Order.asc("age"),Sort.Order.asc("name")));//多字段排序
        return mongoTemplate.find(query, User.class, TABLE_NAME);
    }

    @Override
    public UpdateResult updateFirst(String name, String newName, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("name", newName);
        return mongoTemplate.updateFirst(query, update, TABLE_NAME);
    }

    @Override
    public UpdateResult updateMulti(String name, String newName, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("name", newName);
        return mongoTemplate.updateMulti(query, update, TABLE_NAME);
    }

    @Override
    public DeleteResult remove(String userName, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(userName));
        return mongoTemplate.remove(query, TABLE_NAME);
    }

    @Override
    public long count(String name, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.count(query, TABLE_NAME);
    }
}
