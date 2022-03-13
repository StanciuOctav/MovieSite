package com.movie.moviesite.validator;

import java.util.Collection;

public class ValidateRepo {
    public static boolean isNotEmptyOrNull(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

}
