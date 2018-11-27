package com.zongze.mapper;
import com.zongze.annotation.DynamicDatasource;
import com.zongze.domain.User;

/**
 * Create By xzz on 2018/11/9
 */
public interface UserMapper {


    @DynamicDatasource("master")
    int add(User user);

    @DynamicDatasource("slave")
    User find();

    @DynamicDatasource("slave02")
    int addUser(User user);


}
