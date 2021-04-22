package com.zongze.mongo.service;
import com.alibaba.fastjson.JSON;
import com.zongze.mongo.domain.PageResult;
import com.zongze.mongo.domain.User;
import com.zongze.mongo.util.ApplicationContextHolder;
import com.zongze.mongo.util.MongoCondition;
import com.zongze.mongo.util.MongodbUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


/**
 * @Date 2020/8/15 11:48
 * @Created by xzz
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbUtilTest implements ApplicationContextAware {


    @Autowired
    private MongodbTestService mongodbTestService;
    private static String table = "user";
    private static Class aClass = User.class;

    @Test
    public void add() {
        User user = new User(2,"张三丰", 100);
        MongodbUtil.insertOne("user", user);
    }

    @Test
    public void addAll() {
        List<User>users = new ArrayList<>();
        String[] name = {"张三", "李四", "王五"};
        Random random = new Random();
        for (int i=10;i<50;i++){
            int n = i%3;
            users.add(new User(i,name[n],Math.abs( random.nextInt(99))));
        }
        Collection<User> result = MongodbUtil.insertBatch(table, users);
        System.out.println(result);
    }


    @Test
    public void select() {
        MongoCondition condtion = MongoCondition.newConditionBuilder().addCondition("name", "张三丰").build();
        User user = MongodbUtil.queryOne("user", User.class, condtion);
        System.out.println(user);
    }

    @Test
    public void queryBatch() {
        MongoCondition condtion = MongoCondition.newConditionBuilder()
                .addCondition("name", "张三")
                .addOrderCondition("age", MongoCondition.OrderModel.ASC)
                .build();
        List<User> users = MongodbUtil.queryList(table, aClass, condtion);
        System.out.println(users);
    }

    @Test
    public void queryPage() {
        MongoCondition condtion = MongoCondition.newConditionBuilder()
                .addCondition("name", "张三")
                .addOrderCondition("age", MongoCondition.OrderModel.ASC)
                .addPageCondition(2, 10)
                .build();
        PageResult pageResult = MongodbUtil.queryPage(table, aClass, condtion);
        System.out.println(JSON.toJSONString(pageResult));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.setApplicationContext(applicationContext);
    }
}