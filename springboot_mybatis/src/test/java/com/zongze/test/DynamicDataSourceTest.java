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
        for (int i=1;i<18;i++){
            slave1Service.add(user);
        }
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


    //pageHelper插件使用
    @Test
    public void findAll(){
        slave1Service.pageHelperTest();
    }


    //测试事务,service层出现runTimeException就会促发事务回滚
    @Test
    public void testTransaction(){
        User user = new User();
        user.setUserName("slave2");
        user.setAge(28);
        slave1Service.add(user);
    }


    //测试slave1Service调用slave2Service
    @Test
    public void test_slave1_2(){
      slave1Service.slave1_2();
    }




}
