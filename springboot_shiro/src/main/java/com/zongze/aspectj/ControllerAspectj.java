package com.zongze.aspectj;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Create By xzz on 2019/2/21
 */
@Aspect
@Component
public class ControllerAspectj {


    @Pointcut("execution(* com.zongze.controller.*.*(..))")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object doPoint(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        System.out.println(JSON.toJSONString(point.getTarget().getClass()));
        System.out.println(JSON.toJSONString(point.getSignature().getName()));
        System.out.println(JSON.toJSONString(point.getTarget().getClass().getSuperclass()));
        System.out.println(JSON.toJSONString(proceed));
        return proceed;
    }



}
