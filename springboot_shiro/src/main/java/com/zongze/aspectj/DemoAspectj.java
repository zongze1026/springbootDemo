package com.zongze.aspectj;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Create By xzz on 2019/2/21
 */
@Controller
@Aspect
public class DemoAspectj {


    @Pointcut("execution(* com.zongze.controller.*.*(..))")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object doPoint(ProceedingJoinPoint point) throws Throwable {

        /*//获取目标对象
        Object target = point.getTarget();

        //获取代理对象
        Object proxy = point.getThis();

        //获取目标方法名称
        String methodName = point.getSignature().getName();

        //获取目标方法字节码对象
        Class<?> clazz = target.getClass();

        //获取目标方法的所有参数
        Object[] args = point.getArgs();

        //获取连接点类型
        String kind = point.getKind();

        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();*/

        Object proceed = point.proceed();
        return proceed;


    }





}
