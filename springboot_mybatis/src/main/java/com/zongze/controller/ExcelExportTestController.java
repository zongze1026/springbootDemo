package com.zongze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2020/8/17 17:00
 * @Created by xzz
 */
@RestController
public class ExcelExportTestController {


    @GetMapping("testExport")
    public void test(HttpServletResponse response) throws IOException {
        List<User> users = new ArrayList<User>();

//        for (int i = 0; i < 65540; i++) {
//            users.add(new User("zhangsan", 20, 522L, new Date()));
//        }
        ExcelUtilCopy.exportExcel(users, response, User.class, "报表导出测试");

    }


}
