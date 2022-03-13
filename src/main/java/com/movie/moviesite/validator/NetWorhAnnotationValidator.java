package com.movie.moviesite.validator;

import com.movie.moviesite.annotations.NetWorthAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NetWorhAnnotationValidator implements ConstraintValidator<NetWorthAnnotation, Long> {

    @Override
    public void initialize(NetWorthAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong != null && aLong >= 0;
    }
}
