package com.movie.moviesite.controller;

import com.movie.moviesite.service.ReviewedByUsersServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/review")
public class ReviewController {
    private final ModelMapper modelMapper;
    private final ReviewedByUsersServiceImpl reviewService;

    public ReviewController(ModelMapper modelMapper, ReviewedByUsersServiceImpl reviewService) {
        this.modelMapper = modelMapper;
        this.reviewService = reviewService;
    }

    @PostMapping("/{movieId}/{email}/{reviewContent}")
    public ResponseEntity<?> addReview(@PathVariable("movieId") Long movieId,
                                       @PathVariable("email") String email,
                                       @PathVariable("reviewContent") String reviewContent) {
        this.reviewService.addReview(movieId, email, reviewContent);
        return ResponseEntity.ok("Review added/updated");
    }

    @DeleteMapping("/{movieId}/{email}")
    public ResponseEntity<?> deleteReview(@PathVariable("movieId") Long id,
                                          @PathVariable("email") String email) {
        this.reviewService.deleteReview(id, email);
        return ResponseEntity.ok("Review was deleted");
    }
}
