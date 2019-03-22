package com.zongze.test;

import com.alibaba.fastjson.JSON;
import com.zongze.entity.Menu;
import com.zongze.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Create By xzz on 2019/3/21
 */
@Getter
@Setter
public class Test01 {

    private String name;

    public Integer age;


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setId(10);
        menu.setMenuName("权限管理");
        menu.setMenuPerm("pro");
        menu.setParentId(58);
        menu.setPageNum(52);
        menu.setStart(new Date());

//        User user = ObjectUtil.convert(menu, User.class);
        User user1 = new User("zhangsan");
        BeanUtils.copyProperties(menu,user1);
//        User user2 = ObjectUtil.convertTest(menu, User.class);
//        System.out.println(JSON.toJSONString(user2));
//        System.out.println(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(user1));
    }


}
