package com.zongze;
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
    public void sell(){
        for (int i=0;i<200;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String lianyiqun = pService.sell("lianyiqun");
                    System.out.println(lianyiqun);
                }
            }).start();
        }
    }


    @Test
    public void sellSync(){
        for (int i=0;i<150;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String lianyiqun = pService.sellSync("lianyiqun");
                    System.out.println(lianyiqun);
                }
            }).start();
        }
    }





}

