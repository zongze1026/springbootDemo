package com.zongze.service;

import com.zongze.entity.User;
import com.zongze.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Create By xzz on 2019/3/8
 */
@Service
public class pService {


    @Autowired
    private UserDao userDao;



    public String sell(String name){
        User user = userDao.userInfo(name);
        if(user.getAge()>0){
            int sell = userDao.sell(user);
            if(sell>0){
                return "成功购买";
            }else{
                return "商品已经售完";
            }
        }
        return "商品已经售完";
    }

    public String sellSync(String name){
        User user = userDao.userInfo(name);
        if(user.getAge()>0){
            int sell = userDao.sellSync(user);
            if(sell>0){
                return "成功购买";
            }else{
                return "商品已经售完";
            }
        }
        return "商品已经售完";
    }









}
