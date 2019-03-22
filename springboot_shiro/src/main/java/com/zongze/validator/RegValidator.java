package com.zongze.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Create By xzz on 2019/3/22
 */
public class RegValidator implements ConstraintValidator<Phone, String> {

    private Pattern pattern = null;

    private boolean allowEmpty = false;

    @Override
    public void initialize(Phone annotation) {
        Object annotationValue = getAnnotationValue(annotation, "value");
        if (null != annotationValue) {
            pattern = Pattern.compile((String) annotationValue);
        }
        Object empty = getAnnotationValue(annotation, "allowEmpty");
        if (null != empty) {
            allowEmpty = (Boolean) empty;
        }
    }

    private Object getAnnotationValue(Annotation annotation, String methodName) {
        try {
            Method method = annotation.getClass().getMethod(methodName);
            Object object = method.invoke(annotation);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean isValid(String target, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(target) && allowEmpty) {
            return true;
        }
        if (StringUtils.isBlank(target) || null == pattern) {
            return false;
        }
        return pattern.matcher(target).matches();
    }
}
