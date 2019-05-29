package com.zongze.ref;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieZZ on 2019/2/24
 */
public class ObjectUtil {


    public static Map<String, Field> objectToMap(Object object) {

        Map<String, Field> fields = new HashMap<>();
        Class clazz = object.getClass();
        while (clazz != null && !"java.lang.object".equals(clazz.getName().toLowerCase())) {
            for (Field field : clazz.getDeclaredFields()) {
                fields.put(field.getName(), field);
            }
            clazz = clazz.getSuperclass();
        }

        return fields;


    }


    public static void main(String[] args) {

        Map<String, Field> fi = objectToMap(new User());
        for (Map.Entry entry:fi.entrySet()){
            System.out.println(entry.getKey());
        }
    }


}
