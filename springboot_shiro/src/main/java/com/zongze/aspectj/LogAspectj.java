package com.zongze.aspectj;

import com.alibaba.fastjson.JSON;
import com.zongze.annotation.Log;
import com.zongze.entity.Logger;
import com.zongze.entity.enmu.OperatorType;
import com.zongze.service.LogService;
import com.zongze.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Create By xzz on 2019/3/26
 */
@Aspect
@Component
public class LogAspectj {

    @Autowired
    private LogService logService;


    @Pointcut("execution(* com.zongze.controller.*.*(..))")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object target = point.getTarget();
        Object result = point.proceed();
        String methodName = point.getSignature().getName();
        if (ObjectUtil.getMethod(target.getClass(), methodName).isAnnotationPresent(Log.class)) {
            Integer operatorCode = null;
            Object arg = null;
            Object[] args = point.getArgs();
            out:
            for (Object object : args) {
                if (object instanceof ShiroHttpServletRequest) {
                    continue;
                }
                for (Field field : ObjectUtil.getFields(object.getClass())) {
                    if (StringUtils.equals(field.getName(), "operatorType")) {
                        field.setAccessible(true);
                        operatorCode = (Integer) field.get(object);
                        arg = object;
                        break out;
                    }
                }
            }
            OperatorType operatorType = OperatorType.getType(operatorCode);
            addLog(methodName, request, target, result, arg, operatorType);
        }
        return result;
    }

    private void addLog(String methodName, HttpServletRequest request, Object target, Object result, Object arg, OperatorType operatorType) {
        Log classAnnotation = target.getClass().getAnnotation(Log.class);
        String title = ObjectUtil.invokeMethod(classAnnotation, "title", String.class);
        Method method = ObjectUtil.getMethod(target.getClass(), methodName);
        Log methodAnnotation = method.getAnnotation(Log.class);
        String content = ObjectUtil.invokeMethod(methodAnnotation, "value", String.class);
        Logger logger = new Logger();
        logger.setContent(content + operatorType.getDesc());
        logger.setReqParam(JSON.toJSONString(arg));
        logger.setRespResult(JSON.toJSONString(result));
        logger.setTitle(title);
        logger.setUri(request.getRequestURI());
        logService.add(logger);
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.setUri("http://localhost");
        logger.setTitle("日志测试");
        logger.setRespResult("response");
        logger.setReqParam("request");
        logger.setContent("日志测试");
        logger.setOperatorType(0);
        System.out.println(JSON.toJSONString(logger));
    }


}
