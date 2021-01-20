package com.zongze.mongo.service;

import com.zongze.mongo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.List;


/**
 * @Date 2020/8/16 10:46
 * @Created by xzz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoCollectionTestServiceImplTest {

    @Autowired
    private MongoCollectionTestService mongoCollectionTestService;

    @Test
    public void save() {
        User user = new User("李梅", 58);
        mongoCollectionTestService.save(user);
        System.out.println(mongoCollectionTestService.findOne("李梅", null));
    }

    @Test
    public void batchSave() {
        mongoCollectionTestService.batchSave();
        List<User> users = mongoCollectionTestService.findAll("张无忌", null);
        users.stream().forEach(u-> System.out.println(u));
    }

    @Test
    public void findOne() {
        User user = mongoCollectionTestService.findOne("张无忌", null);
        System.out.println(user);
    }

    @Test
    public void findAll() {
        List<User> users = mongoCollectionTestService.findAll("张无忌", null);
        users.stream().forEach(u-> System.out.println(u));
    }

    @Test
    public void updateFirst() {
        mongoCollectionTestService.updateFirst("张无忌", "张三丰", null);
        System.out.println( mongoCollectionTestService.count("张无忌", null));
    }

    @Test
    public void updateMulti() {
        mongoCollectionTestService.updateMulti("张无忌", "张三丰",null);
        System.out.println( mongoCollectionTestService.count("张无忌", null));
    }

    @Test
    public void remove() {
        mongoCollectionTestService.remove("张三丰", null);
        System.out.println( mongoCollectionTestService.count("张三丰", null));
    }

    @Test
    public void count() {
        System.out.println( mongoCollectionTestService.count("张无忌", null));
    }
}