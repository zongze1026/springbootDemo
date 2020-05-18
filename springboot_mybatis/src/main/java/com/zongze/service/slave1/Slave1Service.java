package com.zongze.service.slave1;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zongze.annotation.DynamicDatasource;
import com.zongze.domain.User;
import com.zongze.mapper.UserMapper;
import com.zongze.service.slave2.Slave2Service;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Create By xzz on 2018/11/27
 */
@Service
public class Slave1Service {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Slave2Service service;

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


    /**
     * 从slave1中调用slave2service 看数据源的变化
     */
    public void slave1_2(){
        User user = userMapper.find();
        System.out.println(JSON.toJSONString(user));
        User user2 = service.user();
        System.out.println(JSON.toJSONString(user2));
    }

    /**
     * 从属性中取值遍历
     */
    public void findAll(){
        User user = new User();
        user.setIds(Arrays.asList("1","2","3"));
        List<User> userList = userMapper.findAllByForEach(user);
        System.out.println(JSON.toJSONString(userList));
    }


    /**
     * 从属性中取值遍历
     */
    public void findAllByIds(){
        List<String> list = Arrays.asList("1", "2", "3");
        List<User> userList = userMapper.findAllByIds(list);
        System.out.println(JSON.toJSONString(userList));
    }









}
