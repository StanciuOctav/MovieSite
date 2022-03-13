package com.movie.moviesite.annotations;

import com.movie.moviesite.validator.AgeAnnotationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AgeAnnotationValidator.class)
@Documented
public @interface AgeAnnotaion {

    String message() default "Age must be between 18 and 99 and not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
