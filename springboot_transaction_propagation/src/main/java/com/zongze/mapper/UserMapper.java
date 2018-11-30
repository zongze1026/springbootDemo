package com.zongze.mapper;

import com.zongze.model.User;

import java.util.List;

/**
 * Create By xzz on 2018/11/9
 */
public interface UserMapper {

    int add(User user);

    User find();

    int addUser(User user);

    List<User> findAll();
}
