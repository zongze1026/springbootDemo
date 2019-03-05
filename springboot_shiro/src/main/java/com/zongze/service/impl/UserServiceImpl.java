package com.zongze.service.impl;

import com.zongze.entity.Menu;
import com.zongze.entity.Role;
import com.zongze.entity.User;
import com.zongze.mapper.UserDao;
import com.zongze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Create By xzz on 2018/12/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User userInfo(String userName) {
        return userDao.userInfo(userName);
    }

    @Override
    public Set<String> getRole(String userName) {
        Set<Role> roles = userDao.geRole(userName);
        Set<String> result = new HashSet<>();
        if (roles.size() > 0) {
            for (Role role : roles) {
                result.add(role.getRoleKey());
            }
        }
        return result;
    }

    @Override
    public Set<String> getPerm(String userName) {
        Set<Menu> perms = userDao.getPerm(userName);
        Set<String> result = new HashSet<>();
        if (perms.size() > 0) {
            for (Menu perm : perms) {
                result.add(perm.getMenuPerm());
            }
        }
        return result;
    }

    @Override
    public int eidt(User user) {
        return userDao.eidt(user);
    }


}
