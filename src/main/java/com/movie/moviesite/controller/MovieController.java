package com.movie.moviesite.controller;

import com.movie.moviesite.model.Movie;
import com.movie.moviesite.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Collection<Movie>> getAllMovies() {
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        return ResponseEntity.ok(this.movieService.getMovieById(movieId));
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie, @RequestParam("director") String directedBy) {
        return this.movieService.saveMovie(movie, directedBy);
    }

    @PatchMapping("/{movieId}")
    public ResponseEntity<?> updateMovieById(@RequestBody Movie movie, @PathVariable Long movieId) {
        return this.movieService.updateMovieById(movie, movieId);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long movieId) {
        return this.movieService.deleteMovieById(movieId);
    }

}
