package com.movie.moviesite.validator;

import com.movie.moviesite.annotations.AgeAnnotaion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeAnnotationValidator implements ConstraintValidator<AgeAnnotaion, Integer> {

    @Override
    public void initialize(AgeAnnotaion constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null && integer >= 18 && integer <= 99;
    }
}
