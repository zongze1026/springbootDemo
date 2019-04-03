package com.zongze.test;

import com.sun.org.apache.xpath.internal.operations.String;
import com.zongze.annotation.Enmu;
import com.zongze.entity.enmu.OperatorType;
import com.zongze.util.ObjectUtil;

import java.lang.reflect.Method;


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


    @Enmu
    public void getAnno(){

    }




}
