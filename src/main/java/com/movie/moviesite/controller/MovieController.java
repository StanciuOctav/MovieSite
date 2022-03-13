package com.movie.moviesite.controller;

import com.movie.moviesite.model.Movie;
import com.movie.moviesite.service.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/movies")
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
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        this.movieService.saveMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @PatchMapping("/{movieId}")
    public ResponseEntity<Movie> updateMovieById(@RequestBody Movie movie, @PathVariable Long movieId) {
        this.movieService.updateMovieById(movie, movieId);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovieById(@PathVariable Long movieId) {
        this.movieService.deleteMovieById(movieId);
        return ResponseEntity.ok("Movie with the id " + movieId + " was deleted");
    }

}
