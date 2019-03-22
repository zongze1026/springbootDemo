package com.zongze.aspectj;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.management.Agent;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.SchemaOutputResolver;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Create By xzz on 2019/2/21
 */
public class ControllerAspectj {


    @Pointcut("execution(* com.zongze.controller.*.*(..))")
    public void pointCut() {
    }


    @Around("pointCut()")
    public void doPoint(ProceedingJoinPoint point) throws Throwable {
//        Object proceed = point.proceed();
//        System.out.println(JSON.toJSONString(point.getTarget().getClass()));
//        System.out.println(JSON.toJSONString(point.getSignature().getName()));
//        System.out.println(JSON.toJSONString(point.getTarget().getClass().getSuperclass()));
//        System.out.println(JSON.toJSONString(proceed));


        Object[] args = point.getArgs();
        Object requestArg = null;
        for (Object object : args) {
            if (!(object instanceof HttpServletRequest)) {
                requestArg = object;
                break;
            }
        }

        Field[] declaredFields = requestArg.getClass().getDeclaredFields();
        for (Field field:declaredFields){

        }


        Class controllerClass = point.getTarget().getClass();
        Method[] declaredMethods = controllerClass.getDeclaredMethods();

        //目标方法
        Method target = null;
        for (
                Method method : declaredMethods) {
            if (method.getName().equals("aop1")) {
                target = method;
                break;
            }
        }


        Annotation annotation = target.getAnnotation(RequestMapping.class);
        Method method = annotation.getClass().getMethod("method");
        Object invoke = method.invoke(annotation);
        System.out.println(JSON.toJSONString(invoke));
        Method method1 = annotation.getClass().getMethod("value");
        System.out.println(JSON.toJSONString(method1.invoke(annotation)));
    }


    public static void main(String[] args) {

        String[] strings = {"a", "b", "c"};
        String join = StringUtils.join(strings, "=");
        System.out.println(join);
    }



}
