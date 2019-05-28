package com.zongze;

import com.alibaba.fastjson.JSON;
import com.zongze.model.User;
import com.zongze.utils.HttpUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private JavaMailSender sender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mailSend(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("13916257992@163.com");
        message.setTo("994711007@qq.com");
        message.setSubject("测试邮件");
        message.setText("邮件测试");
    }

    @Test
    public void testTemplate() throws JSONException {
        User user1 = new User();
        user1.setUserName("老王");
        user1.setAge(18l);
        user1.setPassWord("10201");
        User user = HttpUtil.sendPost("http://localhost:8081/callback/success", user1, User.class);
        System.out.println(JSON.toJSONString(user));
    }





}

