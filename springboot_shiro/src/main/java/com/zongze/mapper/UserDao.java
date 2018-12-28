package com.zongze.mapper;

import com.zongze.entity.Menu;
import com.zongze.entity.Role;
import com.zongze.entity.User;

import java.util.Set;

/**
 * Create By xzz on 2018/12/24
 */
public interface UserDao {

    User userInfo(String userName);

    Set<Role> geRole(String userName);

    Set<Menu> getPerm(String userName);
}
