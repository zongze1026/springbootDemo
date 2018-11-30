package com.zongze.service.slave1;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zongze.annotation.DynamicDatasource;
import com.zongze.domain.User;
import com.zongze.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create By xzz on 2018/11/27
 */
@Service
public class Slave1Service {

    @Autowired
    private UserMapper userMapper;

    /**
     * db slave1使用主数源插入操作
     */
    @DynamicDatasource("master")
    @Transactional
    public void add(User user){
        userMapper.add(user);
        throw new RuntimeException();
    }

    /**
     * db slave1默认使用slave数据源查询
     */
    public void userInfo(){
        User user = userMapper.find();
        System.out.println(JSON.toJSONString(user));
    }



    /**
     * pageHelper使用
     */
    public void pageHelperTest(){
        int pageNum = 1;
        int pageSize = 10;
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.findAll();
        //创建pageInfo对象,分页的所有数据都封装在pageInfo对象中
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //获取到当前的数据集合
        List<User> list = pageInfo.getList();
        list.stream().forEach(user->{
            System.out.println(JSON.toJSONString(user));
        });
        //获取总页数
        int pages = pageInfo.getPages();
        System.out.println("====================总页数："+pages+"============================");
        //获取总条数
        long total = pageInfo.getTotal();
        System.out.println("====================总数量："+total+"============================");

    }





}
