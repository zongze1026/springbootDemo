package com.zongze.annotation;

import com.zongze.model.LockType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2019/12/13
 * 用于分布式锁
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Dlock {

    /**
     * 锁名称
     * @param:
     * @return:
     */
    String name() default "";

    /**
     * 锁的类型，由LockType决定
     * @param:
     * @return:
     */
    LockType lockType() default LockType.Reentrant;

    /**
     * 尝试获取锁，最多等待时间
     * @param:
     * @return:
     */
    long waitTime() default 30;

    /**
     * 时间单位
     * @param:
     * @return:
     */
    TimeUnit unit() default TimeUnit.SECONDS;


}
