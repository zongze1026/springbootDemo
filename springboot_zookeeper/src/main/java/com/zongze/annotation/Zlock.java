package com.zongze.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create By xzz on 2019/12/23
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Zlock {

    /**
     * 锁名称
     * @param:
     * @return:
     */
    String value();

}
