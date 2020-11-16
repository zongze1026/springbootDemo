package com.zongze.freemarker.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Date 2020/11/11 11:11
 * @Created by xiezz
 */
@Documented
@Constraint(validatedBy = {PhoneValidated.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface PhoneCheck {

    String message() default "手机号码不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
