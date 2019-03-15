package com.zongze.test;

import com.alibaba.fastjson.JSON;
import com.zongze.entity.Menu;
import com.zongze.util.ObjectUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Create By xzz on 2019/3/5
 */
public class Test1 extends Abstranct<Integer> {

    protected Test1(Integer integer) {
        super(integer);
    }

    public Integer get(){
        return this.getObject();
    }


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setId(10);
        menu.setMenuName("权限管理");
        menu.setMenuPerm("pro");
        menu.setParentId(58);

        Map<String, Object> stringObjectMap = ObjectUtil.objectToMap(menu);
        System.out.println(JSON.toJSONString(stringObjectMap));

        System.out.println(HashMap.class.isAssignableFrom(LinkedHashMap.class));




    }




}
