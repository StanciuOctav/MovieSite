package com.movie.moviesite.service;

import com.movie.moviesite.model.Movie;
import com.movie.moviesite.repository.MovieRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieService {
    private final MovieRepostitory movieRepostitory;

    @Autowired
    public MovieService(MovieRepostitory movieRepostitory) {
        this.movieRepostitory = movieRepostitory;
    }

    public Collection<Movie> getAllMovies() {
        return this.movieRepostitory.getAllMovies();
    }

    public Collection<Movie> getMoviesDirectedBy(Long id) {
        return this.movieRepostitory.getMoviesDirectedBy(id);
    }
}
