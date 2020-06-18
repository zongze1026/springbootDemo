package com.zongze.test;

import com.zongze.domain.User;
import com.zongze.service.MasterDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create By xzz on 2018/11/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {

    @Autowired
    private MasterDataSourceTestService service;


    @Test
    public void add(){
        User user = new User();
        user.setUserName("令狐聪");
        user.setAge(18);
        int i = service.addUser(user);
        System.out.println(i);
    }



}
