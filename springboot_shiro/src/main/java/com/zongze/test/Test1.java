package com.zongze.test;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.String;
import com.zongze.entity.Menu;
import com.zongze.entity.User;
import com.zongze.util.ObjectUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

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
//        new statictest().test();
    }




}
