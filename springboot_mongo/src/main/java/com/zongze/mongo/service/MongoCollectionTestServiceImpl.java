package com.zongze.mongo.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zongze.mongo.domain.PageResult;
import com.zongze.mongo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static List<User> users =  new ArrayList<User>();

    static{
        String[] name = {"张三", "李四", "王五"};
        Random random = new Random();
        for (int i=0;i<20;i++){
            int n = i%3;
            users.add(new User(i,name[n],Math.abs( random.nextInt(99))));
        }
    }

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

    @Override
    public List<User> queryConditionUser(String userName, Integer minAge, Integer maxAge) {
        Query query = new Query();
        //指定姓名条件
        query.addCriteria(Criteria.where("name").is(userName));
        //mongodb搜索条件：大于（gt）、大于等于（gte）、小于（lt）、小于等于（lte）
        query.addCriteria(new Criteria().andOperator(Criteria.where("age").gte(minAge),Criteria.where("age").lte(maxAge)));
        //根据年龄排序
        query.with(Sort.by(Sort.Order.desc("age")));
        return mongoTemplate.find(query, User.class, TABLE_NAME);
    }


    @Override
    public List<User> queryUserByLike(String userName) {
        //查找名字中包含“三”的用户
        Query query = Query.query(Criteria.where("name").regex(userName));
        return mongoTemplate.find(query, User.class, TABLE_NAME);
    }


    @Override
    public PageResult queryByPage(PageResult pageResult, User user) {
        Query query = new Query();
        //设置查询条件
        if(!StringUtils.isEmpty(user.getName())){
            query.addCriteria(Criteria.where("name").is(user.getName()));
        }
        if(null != user.getAge()&& user.getAge()>0){
            query.addCriteria(Criteria.where("age").is(user.getAge()));
        }
        //获取从条数
        long count = mongoTemplate.count(query, TABLE_NAME);
        pageResult.setTotalNum(count);
        query.with(PageRequest.of(pageResult.getPageNo()-1, pageResult.getPageSize()));
        List<User> users = mongoTemplate.find(query, User.class, TABLE_NAME);
        pageResult.setData(users);
        pageResult.setTotalPage((int)Math.ceil(count/pageResult.getPageSize()));
        return pageResult;
    }















}
