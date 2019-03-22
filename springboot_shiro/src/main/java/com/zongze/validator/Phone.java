package com.zongze.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Create By xzz on 2019/3/22
 */
@Constraint(validatedBy = RegValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Phone {

    String value() default "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

    boolean allowEmpty() default false;

    String message() default "手机号格式有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
