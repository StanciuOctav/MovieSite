package com.movie.moviesite.validate;

import java.util.Collection;

public class ValidateRepo {
    public static boolean isNotEmptyOrNull(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

}
