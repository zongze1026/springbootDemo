package com.zongze.service;
import com.github.pagehelper.PageInfo;
import com.zongze.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Create By xzz on 2018/12/24
 */
public interface UserService {

    User userInfo(String userName);

    Set<String> getRole(String userName);

    Set<String> getPerm(String userName);

    int eidt(User user);

    PageInfo<User> userList(User user);

    List<User> userList1(User user);



}
