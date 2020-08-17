package com.zongze.mongo.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zongze.mongo.domain.User;

import java.util.List;

/**
 * @Date 2020/8/16 10:09
 * @Created by xzz
 */
public interface MongoCollectionTestService {

    User save(User o);

    List<User>batchSave();

    User findOne(String name,String collectionName);

    List<User> findAll(String name,String collectionName);

    UpdateResult updateFirst(String name, String newName, String collectionName);

    UpdateResult updateMulti(String name, String newName,String collectionName);

    DeleteResult remove(String userName, String collectionName);

    long count(String name,String collectionName);







}
