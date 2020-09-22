package com.zongze.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/18 10:37
 * @Created by xzz
 */
@RestController
public class TestExportExcelController {


    @GetMapping("test")
    public void testExport(HttpServletResponse response){
        ArrayList<User> users = new ArrayList<>();
//        for (int i=0;i<100;i++){
//            users.add(new User("toms",12 ,2500.12 ,251l ));
//        }

        ExcelUtil.exportExcel(users,response ,User.class , "人员登记表");
    }



    @PostMapping("import")
    public void testImport(MultipartFile file,int skip) throws IOException {
        ArrayList<User> users = new ArrayList<>();
//        for (int i=0;i<100;i++){
//            users.add(new User("toms",12 ,2500.12 ,251l ));
//        }
        InputStream inputStream = file.getInputStream();
        List<UserImport> userImports = ExcelUtil.importExcel(inputStream, skip, UserImport.class);
        userImports.stream().forEach(user-> System.out.println(user));
    }








}
