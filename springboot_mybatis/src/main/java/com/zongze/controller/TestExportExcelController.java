package com.zongze.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Date 2020/8/18 10:37
 * @Created by xzz
 */
@RestController
public class TestExportExcelController {


    @GetMapping("test")
    public void testExport(HttpServletResponse response){
        ArrayList<User> users = new ArrayList<>();
        for (int i=0;i<100;i++){
            users.add(new User("toms",12 ,2500.12 ,251l ));
        }

        ExcelUtil.exportExcel(users,response ,User.class , "人员登记表");
    }


    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("toms", 12, 2500.12, 251l);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        System.out.println(userVO);

    }





}
