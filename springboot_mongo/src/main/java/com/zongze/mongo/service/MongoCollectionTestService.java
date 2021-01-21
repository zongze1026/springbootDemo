package com.zongze.mongo.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zongze.mongo.domain.PageResult;
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

    /**
     * 查找指定的用户，并且年龄在指定的范围之间;并按照年龄从大到小排序
     * @param userName 用户名
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return java.util.List<com.zongze.mongo.domain.User>
     */
    List<User> queryConditionUser(String userName,Integer minAge,Integer maxAge);



    /**
     * 根据名字模糊查询
     * @param userName
     * @return java.util.List<com.zongze.mongo.domain.User>
     */
    List<User> queryUserByLike(String userName);


    /**
     * 分页查询
     * @param pageResult 分页参数
     * @param user 查询条件
     * @return java.util.List<com.zongze.mongo.domain.User>
     */
    PageResult queryByPage(PageResult pageResult, User user);







}
