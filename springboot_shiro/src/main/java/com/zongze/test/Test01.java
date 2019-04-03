package com.zongze.test;

import com.alibaba.fastjson.JSON;
import com.zongze.annotation.Enmu;
import com.zongze.entity.Menu;
import com.zongze.entity.User;
import com.zongze.entity.enmu.OperatorType;
import com.zongze.util.ObjectUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
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
        Test1 test1 = new Test1(10);
        Class<? extends Test1> clazz = test1.getClass();
        Method method = ObjectUtil.getMethod(clazz, "getAnno");

        Enmu enmu = method.getAnnotation(Enmu.class);
        System.out.println(enmu.value().getDesc());

        OperatorType operatorType = ObjectUtil.invokeMethod(enmu, "value", OperatorType.class);
        System.out.println(operatorType.getDesc());
    }


}
