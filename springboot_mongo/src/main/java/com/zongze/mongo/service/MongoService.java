package com.zongze.mongo.service;

import com.zongze.mongo.domain.User;

/**
 * @Date 2020/8/15 11:45
 * @Created by xzz
 */
public interface MongoService {

    User add(Object o);

    User select(String userName);

    Object update(String oldName,String newName);

    Object remove(String userName);


}
