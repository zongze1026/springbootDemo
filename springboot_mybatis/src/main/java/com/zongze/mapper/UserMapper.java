package com.zongze.mapper;
import com.zongze.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By xzz on 2018/11/9
 */
public interface UserMapper {

    int add(User user);

    User find();

    int addUser(User user);

    List<User> findAll();

    List<User> findAllByForEach(User user);

    List<User> findAllByIds(@Param("ids") List<String> ids);


}
