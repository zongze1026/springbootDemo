package com.zongze.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create By xzz on 2019/3/22
 */
public class ObjectUtil {


    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    private ObjectUtil() {
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


    /**
     * target 目标对象
     * methodName 目标方法名称
     * clazz 返回指类型
     * args 参数
     */
    public static final <T> T invokeMethod(Object target, String methodName, Class<T> clazz, Object... args) {
        try {
            Method method = getMethod(target.getClass(), methodName);
            method.setAccessible(true);
            return (T) method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * target 目标对象
     * fieldName 属性名称
     * clazz 返回指类型
     */
    public static final <T> T invokeField(Object target, String fieldName, Class<T> clazz) {
        try {
            Field field = getField(target.getClass(), fieldName);
            field.setAccessible(true);
            return (T) field.get(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
