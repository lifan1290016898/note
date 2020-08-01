package com.demo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = PhoneHandler.class)
public @interface Phone {

    String message() default "输入的联系方式不正确";
    /**
     * Phone: 手机号码
     * Fax: 传真
     * Landline: 座机
     */
    String type();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
