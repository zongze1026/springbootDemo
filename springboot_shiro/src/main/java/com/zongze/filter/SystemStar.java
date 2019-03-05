package com.zongze.filter;

import com.alibaba.fastjson.JSON;
import com.zongze.service.UserService;

import java.util.Set;

/**
 * Create By xzz on 2019/2/25
 */
public class SystemStar {


    public static void start(){
        UserService service = ApplicationContextHolder.getBean(UserService.class);
        Set<String> zhangsan = service.getRole("zhangsan");
        System.out.println(JSON.toJSONString(zhangsan));
    }



}
