package com.zongze.aspect;

import com.zongze.annotation.DynamicDatasource;
import com.zongze.config.DynamicDataSourceHolder;
import com.zongze.config.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Create By xzz on 2018/11/21
 * 针对不同的service层使用不同的slave数据源
 * 如果需要添加或者修改数据就是用master数据源
 */
@Aspect
@Component
@Order(-100)
public class DynamicDataSourceAspect {


    /**
     * 移除数据源匹配的切面
     */
    @Pointcut("execution(* com.zongze.service..*.*(..))")
    public void pointCut() {
    }


    /**
     * 选择数据源
     */
    @Before("pointCut()")
    public void setSlave1DataSource(JoinPoint point) {
        selectDataSource(point, DataSourceType.MASTER);
    }



    /**
     * 如果有注解标注的话优先使用注解的数据源
     * 否则使用默认数据源
     */
    private void selectDataSource(JoinPoint point, DataSourceType dataSourceType) {

        try {
            //获取当前方法的字节码对象
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            //判断是否有DynamicDatasource注解
            if (method.isAnnotationPresent(DynamicDatasource.class)) {
                DynamicDatasource annotation = method.getAnnotation(DynamicDatasource.class);
                String key = annotation.value();
                DynamicDataSourceHolder.setKey(key);
                System.out.println("================选择dataSource的key：" + key + "=================");
            } else {
                DynamicDataSourceHolder.setKey(dataSourceType.getKey());
                System.out.println("================选择dataSource的key：" + dataSourceType.getKey() + "=================");
            }
        } catch (Exception e) {
            System.out.println("============异常出现=================");
        }
    }


    /**
     * 清除数据源
     */
    @After("pointCut()")
    public void removeDataSource() {
        DynamicDataSourceHolder.clear();
        System.out.println("===============数据源清除=====================");
    }


}
