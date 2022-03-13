package com.movie.moviesite.annotations;

import com.movie.moviesite.validator.BornInAnnotaionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = BornInAnnotaionValidator.class)
@Documented
public @interface BornInAnnotation {

    String message() default "bornIn field must not be null or blank and needs to be of only 2 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
