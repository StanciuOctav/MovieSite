package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.ReviewedByUserService;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.relationship.ReviewedByUser;
import com.movie.moviesite.repository.MovieRepostitory;
import com.movie.moviesite.repository.ReviewedByUsersRepository;
import com.movie.moviesite.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReviewedByUsersServiceImpl implements ReviewedByUserService {

    private final MovieRepostitory movieRepostitory;
    private final UserRepository userRepository;
    private final ReviewedByUsersRepository reviewedByUsersRepository;

    public ReviewedByUsersServiceImpl(MovieRepostitory movieRepostitory, UserRepository userRepository, ReviewedByUsersRepository reviewedByUsersRepository) {
        this.movieRepostitory = movieRepostitory;
        this.userRepository = userRepository;
        this.reviewedByUsersRepository = reviewedByUsersRepository;
    }

    @Override
    public void addReview(Long movieId, String email, String reviewContent) {
        ReviewedByUser reviewedByUser = new ReviewedByUser();
        reviewedByUser.setUser(this.userRepository.findByEmail(email));
        reviewedByUser.setContent(reviewContent);

        Movie movie = this.movieRepostitory.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with the id " + movieId + " doesn't exist"));
        for (ReviewedByUser ru : movie.getReviewedByUsers()) {
            if (ru.getUser().getEmail().equals(email)) {
                movie.getReviewedByUsers().remove(ru);
                break;
            }
        }
        movie.getReviewedByUsers().add(reviewedByUser);
        this.movieRepostitory.save(movie);
    }

    public void deleteReview(Long id, String email) {
        Movie movie = this.movieRepostitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with the id " + id + " doesn't exist"));
        for (ReviewedByUser ru : movie.getReviewedByUsers()) {
            if (ru.getUser().getEmail().equals(email)) {
                movie.getReviewedByUsers().remove(ru);
                break;
            }
        }
        this.movieRepostitory.save(movie);
    }
}
