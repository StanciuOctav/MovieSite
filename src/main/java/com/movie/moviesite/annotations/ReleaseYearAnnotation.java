package com.movie.moviesite.annotations;

import com.movie.moviesite.validator.ReleaseYearAnnotationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ReleaseYearAnnotationValidator.class)
@Documented
public @interface ReleaseYearAnnotation {

    String message() default "Released year must be between 1888 and current year and not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
