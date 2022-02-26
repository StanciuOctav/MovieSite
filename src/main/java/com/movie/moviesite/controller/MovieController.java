package com.movie.moviesite.controller;

import com.movie.moviesite.model.Movie;
import com.movie.moviesite.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Collection<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/director/{id}")
    public Collection<Movie> getMoviesDirectedBy(@PathVariable("id") Long id) {
        return this.movieService.getMoviesDirectedBy(id);
    }
}
