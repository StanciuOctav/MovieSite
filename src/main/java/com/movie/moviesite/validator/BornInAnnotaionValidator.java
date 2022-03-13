package com.movie.moviesite.validator;

import com.movie.moviesite.annotations.BornInAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BornInAnnotaionValidator implements ConstraintValidator<BornInAnnotation, String> {

    @Override
    public void initialize(BornInAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.length() == 2;
    }
}
