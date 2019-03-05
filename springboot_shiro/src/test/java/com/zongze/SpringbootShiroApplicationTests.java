package com.zongze;
import com.zongze.entity.User;
import com.zongze.service.impl.UserServiceImpl;
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

    @Test
    public void contextLoads() {

    }

    @Test
    public void edit(){
        User user = new User();
        user.setUserName("editTest");
        user.setId(6l);
        userService.eidt(user);
    }


}

