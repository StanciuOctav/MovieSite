package com.movie.moviesite.annotations;

import com.movie.moviesite.validator.NetWorhAnnotationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NetWorhAnnotationValidator.class)
@Documented
public @interface NetWorthAnnotation {

    String message() default "Net worth must be between 0 and 2^63-1 and not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
