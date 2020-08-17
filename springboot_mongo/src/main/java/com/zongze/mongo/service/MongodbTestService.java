package com.zongze.mongo.service;

import com.zongze.mongo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * @Date 2020/8/15 11:41
 * @Created by xzz
 */
@Service
public class MongodbTestService implements MongoService{


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public User add(Object o) {
        return (User) mongoTemplate.save(o);
    }

    @Override
    public User select(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(userName));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public Object update(String oldName,String newName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(oldName));
        Update update = new Update();
        update.set("name", newName);
        update.set("clazz", "高中二班");
        return mongoTemplate.updateFirst(query,update, User.class);
    }

    @Override
    public Object remove(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(userName));
        return  mongoTemplate.remove(query, User.class);
    }

    @Override
    public long count(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.count(query, User.class);
    }


}
