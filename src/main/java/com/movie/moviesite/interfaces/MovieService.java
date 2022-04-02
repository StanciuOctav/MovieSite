package com.movie.moviesite.interfaces;

import com.movie.moviesite.model.Movie;

import java.util.Collection;

public interface MovieService {
    Collection<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie createMovie(Movie movie, String directedBy);

    Movie updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);
}
