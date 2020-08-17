package com.zongze.mongo.service;
import com.zongze.mongo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Date 2020/8/15 11:48
 * @Created by xzz
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbTestServiceTest {


    @Autowired
    private MongodbTestService mongodbTestService;

    @Test
    public void add() {
        User user = new User("张三丰", 100);
        mongodbTestService.add(user);
    }


    @Test
    public void select() {
        User user = mongodbTestService.select("张三丰");
        System.out.println(user);
    }

    @Test
    public void update() {
        mongodbTestService.update("张三丰", "张无忌");
        User user = mongodbTestService.select("张无忌");
        System.out.println(user);

    }

    @Test
    public void remove() {
        mongodbTestService.remove("张无忌");
        User user = mongodbTestService.select("张无忌");
        System.out.println(user);
    }

    @Test
    public void count(){
        long number = mongodbTestService.count("张无忌");
        System.out.println(number);
    }

}