package com.movie.moviesite.interfaces;

public interface ReviewedByUserService {
    void addReview(Long movieId, String email, String reviewContent);
}
