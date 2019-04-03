package com.zongze.annotation;


import com.zongze.component.EntityValidator;
import com.zongze.entity.enmu.OperatorType;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Constraint(validatedBy = EntityValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Enmu {

    OperatorType value() default OperatorType.LOG_ADD;

}
