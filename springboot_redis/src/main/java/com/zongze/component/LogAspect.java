package com.zongze.component;

import com.alibaba.fastjson.JSON;
import com.zongze.util.IPAddressHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut("execution(public * com.zongze.controller.*.*(..))")
    public void pointCutMethod() {
    }


    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        List<Object> args = new ArrayList<>();
        for (Object arg : point.getArgs()) {
            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse) {
                continue;
            }
            args.add(arg);
        }
        String uri = request.getRequestURI();
        logger.info("请求地址: {},请求ip:{},请求参数：{}", uri, IPAddressHelper.getClientIP(request), JSON.toJSONString(args));
        Object result = point.proceed();
        logger.info("响应结果: {}", JSON.toJSONString(result));
        return result;
    }


}