package com.movie.moviesite.validator;

import com.movie.moviesite.annotations.ReleaseYearAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ReleaseYearAnnotationValidator implements ConstraintValidator<ReleaseYearAnnotation, Integer> {

    @Override
    public void initialize(ReleaseYearAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = LocalDate.now().getYear();
        return integer != null && integer >= 1888 && integer <= currentYear;
    }
}
