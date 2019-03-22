package com.zongze.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = RegValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Password {

    String value() default "^[A-Za-z0-9]{6,18}$";

    String message() default "密码格式不符合要求，请输入6-18位密码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
