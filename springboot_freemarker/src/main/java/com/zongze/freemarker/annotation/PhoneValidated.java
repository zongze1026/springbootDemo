package com.zongze.freemarker.annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Date 2020/11/11 11:12
 * @Created by xiezz
 */
public class PhoneValidated implements ConstraintValidator<PhoneCheck, String> {
    @Override
    public void initialize(PhoneCheck constraintAnnotation) {
        String message = constraintAnnotation.message();
        System.out.println(this);
        System.out.println(message+"==="+this.getClass().toString());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.isEmpty(s);
    }
}
