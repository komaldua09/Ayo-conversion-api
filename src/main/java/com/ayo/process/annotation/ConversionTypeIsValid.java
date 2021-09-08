package com.ayo.process.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ConversionTypeValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface ConversionTypeIsValid {
    String message() default "{The request payload is invalid}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
