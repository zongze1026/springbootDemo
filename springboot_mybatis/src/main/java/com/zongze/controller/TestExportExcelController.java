package com.zongze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Date 2020/8/18 10:37
 * @Created by xzz
 */
@RestController
public class TestExportExcelController {


    @GetMapping("test")
    public void testExport(HttpServletResponse response) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            users.add(new User("toms", 12, 2500.12, 251l));
        }

        ExcelUtil.exportExcel(users, response, User.class, "人员登记表");
    }


    @GetMapping("test01")
    public void test01(HttpServletResponse response) throws IOException {
        int i = 1/0;
    }


    @GetMapping("test02")
    public void testExportB(HttpServletResponse response) throws IOException {

        Map<String, List<User>> map = new HashMap<>();
        ArrayList<User> user1 = new ArrayList<>();
        ArrayList<User> user2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            user1.add(new User("toms", 12, 20, 251l));
            user2.add(new User("toms", 12, 20, 251l));
        }
        map.put("第一页数据", user1);
        map.put("第二页数据", user2);

        ExcelUtil.exportExcel(map, response, User.class, "人员登记表");
    }


    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("toms", 12, 20, 251l));
        list.add(new User("toms", 12, 20, 251l));
        list.add(new User("toms", 13, 20, 251l));
        list.add(new User("toms", 14, 20, 251l));
        list.add(new User("toms", 14, 20, 251l));
        list.add(new User("toms", 18, 20, 251l));
        list.add(new User("toms", 18, 20, 251l));
        list.add(new User("toms", 15, 20, 251l));
        list.add(new User("toms", 20, 20, 251l));

//        Map<Integer, List<User>> groupList = list.stream().collect(Collectors.groupingBy(user -> user.getAge()));
//        System.out.println(groupList.size());
//        for (Map.Entry<Integer,List<User>> entry:groupList.entrySet()){
//            Integer key = entry.getKey();
//            System.out.println(key);
//            List<User> value = entry.getValue();
//            value.stream().forEach(user -> System.out.println(user));
//        }

        Map<Integer, Double> collect = list.stream().collect(Collectors.groupingBy(user -> user.getAge(), Collectors.summingDouble(user -> user.getPrice())));
        System.out.println(collect.size());
        for (Map.Entry<Integer, Double> entry : collect.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue());


        }

    }


}
