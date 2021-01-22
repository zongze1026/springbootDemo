package com.zongze.mongo.service;

import com.alibaba.fastjson.JSON;
import com.zongze.mongo.domain.PageResult;
import com.zongze.mongo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Random;


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
        User user = new User(5, "张无忌", 100);
        mongoCollectionTestService.save(user);
        System.out.println(mongoCollectionTestService.findOne("李梅", null));
    }

    @Test
    public void batchSave() {
        String[] name = {"张三", "李四", "王五"};
        Random random = new Random();
        for (int i=0;i<500000;i++){
            int n = i%3;
            User user = new User(i, name[n], Math.abs(random.nextInt(99)));
            mongoCollectionTestService.save(user);
        }
//        List<User> users = mongoCollectionTestService.findAll("张无忌", null);
//        users.stream().forEach(u -> System.out.println(u));
    }

    @Test
    public void findOne() {
        User user = mongoCollectionTestService.findOne("张无忌", null);
        System.out.println(user);
    }

    @Test
    public void findAll() {
        List<User> users = mongoCollectionTestService.findAll("张无忌", null);
        users.stream().forEach(u -> System.out.println(u));
    }

    @Test
    public void updateFirst() {
        mongoCollectionTestService.updateFirst("张无忌", "张三丰", null);
        System.out.println(mongoCollectionTestService.count("张无忌", null));
    }

    @Test
    public void updateMulti() {
        mongoCollectionTestService.updateMulti("张无忌", "张三丰", null);
        System.out.println(mongoCollectionTestService.count("张无忌", null));
    }

    @Test
    public void remove() {
        mongoCollectionTestService.remove("张三丰", null);
        System.out.println(mongoCollectionTestService.count("张三丰", null));
    }

    @Test
    public void count() {
        System.out.println(mongoCollectionTestService.count("张无忌", null));
    }


    @Test
    public void queryConditionUser() {
        List<User> users = mongoCollectionTestService.queryConditionUser("张三", 50, 100);
        users.stream().forEach(u -> System.out.println(u));
    }


    @Test
    public void queryUserByLike() {
        List<User> users = mongoCollectionTestService.queryUserByLike("三");
        users.stream().forEach(u -> System.out.println(u));
    }


    @Test
    public void queryByPage() {
        long start = System.currentTimeMillis();
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPageNo(1);
        pageResult.setPageSize(20);
        pageResult = mongoCollectionTestService.queryByPage(pageResult, new User());
        System.out.println(JSON.toJSONString(pageResult));
        long l = System.currentTimeMillis() - start;
        System.out.println("查询耗时:"+l);
    }



    /**
     * 测试分页性能；每隔1000页拉一次数据
     * @param
     * @return void
     */
    @Test
    public void queryByPage02() {
        for (int i = 1; i <= 25; i++) {
            long start = System.currentTimeMillis();
            PageResult<User> pageResult = new PageResult<>();
            int n = i * 1000;
            pageResult.setPageNo(n);
            pageResult.setPageSize(20);
            pageResult = mongoCollectionTestService.queryByPage(pageResult, new User());
            long l = System.currentTimeMillis() - start;
            System.out.println("查询页码：" + n + "查询耗时:" + l+"查询的数据总数："+pageResult.getData().size());
        }
    }


    /**
     * 测试索引
     *
     * @param
     * @return void
     */
    @Test
    public void testIndex() {
        //name有素引
        User user = new User();
        user.setName("张三");
        queryPageList(user);

        //age没有索引
        User user02 = new User();
        user02.setAge(42);
        queryPageList(user);

    }

    private void queryPageList(User user) {
        long start = System.currentTimeMillis();
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPageNo(2);
        pageResult.setPageSize(2000);
        pageResult = mongoCollectionTestService.queryByPage(pageResult, user);
        long l = System.currentTimeMillis() - start;
        System.out.println("查询页码：" + 2 + "查询耗时:" + l+"查询的数据总数："+pageResult.getData().size());
    }


}