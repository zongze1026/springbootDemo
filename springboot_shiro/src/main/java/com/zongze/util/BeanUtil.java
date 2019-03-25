package com.zongze.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create By xzz on 2019/3/22
 */
public class BeanUtil {


    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    public static <T> T copyProperties(Object source, Class<? extends T> targetClass) {
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Method getMethod(Class oClass, String methodName) {
        List<Method> methods = getMethods(oClass);
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }


    public static final Field getField(Class oClass, String fieldName) {
        List<Field> fields = getFields(oClass);
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }


    public static final List<Method> getMethods(Class oClass) {
        List<Method> methods = new ArrayList<>();
        Class tempClass = oClass;
        while (tempClass != null && !Object.class.getName().equals(tempClass.getName())) {
            methods.addAll(Arrays.asList(tempClass.getDeclaredMethods()));
            tempClass = tempClass.getSuperclass();
        }
        return methods;
    }


    public static final List<Field> getFields(Class oClass) {
        List<Field> fields = new ArrayList<>();
        Class tempClass = oClass;
        while (tempClass != null && !Object.class.getName().equals(tempClass.getName())) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        return fields;
    }


}
