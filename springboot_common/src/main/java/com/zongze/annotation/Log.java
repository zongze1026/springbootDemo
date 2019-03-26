package com.zongze.annotation;


import java.lang.annotation.*;

/**
 * 自定义日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Log {

    String value() default "";

    String title() default "";

}