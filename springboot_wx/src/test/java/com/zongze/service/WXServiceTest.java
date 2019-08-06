package com.zongze.service;

import com.zongze.entity.AccessToken;
import com.zongze.entity.WXConstants;
import com.zongze.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Create By xzz on 2019/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WXServiceTest {

    @Autowired
    WechatService wxService;

    @Test
    public void createMenu() {
        boolean menu = wxService.createMenu();
    }


    //用户基本信息
    @Test
    public void userInfo() {
        AccessToken token = wxService.getToken();
        String userInfo = WXConstants.getUserInfo(token.getAccessToken(), "ofhzw5-6HKyV8V-i_tdfN9CvyWc4");
        String s = HttpUtil.get(userInfo);
        System.out.println(s);
    }


}