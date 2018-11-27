package com.zongze.annotation;
import java.lang.annotation.*;

/**
 * Create By xzz on 2018/11/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Documented
public @interface DynamicDatasource {

    String value() default "master";

}
