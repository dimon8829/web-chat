package com.dihri.web.chat.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for check login
 */
@Documented
@Constraint(validatedBy = IsExistsLoginValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsLogin {
    String message() default "login already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
