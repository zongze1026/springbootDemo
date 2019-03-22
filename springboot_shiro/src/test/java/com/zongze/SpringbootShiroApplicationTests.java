package com.zongze;
import com.alibaba.fastjson.JSON;
import com.zongze.entity.User;
import com.zongze.service.impl.UserServiceImpl;
import com.zongze.service.pService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private pService pService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void edit(){
    }


    @Test
    public void pageTest(){
        User user = new User();
        user.setUserName("zhangsan");
        user.setPageNum(1);
        user.setPageSize(3);
        Object o = pService.pageTest(user);
        System.out.println(JSON.toJSONString(o));
    }



}

