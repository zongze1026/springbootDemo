package com.zongze.service;
import com.zongze.entity.User;
import java.util.Set;

/**
 * Create By xzz on 2018/12/24
 */
public interface UserService {

    User userInfo(String userName);

    Set<String> getRole(String userName);

    Set<String> getPerm(String userName);

    int eidt(User user);



}
