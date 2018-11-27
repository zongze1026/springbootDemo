package com.zongze.test;

import com.zongze.domain.User;
import com.zongze.service.slave1.Slave1Service;
import com.zongze.service.slave2.Slave2Service;
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
    private Slave1Service slave1Service;

    @Autowired
    private Slave2Service slave2Service;

    @Test
    public void slave1add(){
        User user = new User();
        user.setUserName("slave1");
        user.setAge(18);
        slave1Service.add(user);
    }


    @Test
    public void slave2add(){
        User user = new User();
        user.setUserName("slave2");
        user.setAge(28);
        slave1Service.add(user);
    }


    @Test
    public void slave2find(){
        slave2Service.userInfo();
    }


    @Test
    public void slave1find(){
        slave1Service.userInfo();
    }




}
