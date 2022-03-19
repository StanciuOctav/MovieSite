package com.movie.moviesite.service;

import com.movie.moviesite.model.Movie;
import com.movie.moviesite.repository.MovieRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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

    public Collection<Movie> getReviewedMovies() {
        return this.movieRepostitory.getReviewedMovies();
    }

    public Movie getMovieById(Long movieId) {
        return this.movieRepostitory.getMovieById(movieId);
    }

    public void saveMovie(Movie movie) {
        this.movieRepostitory.save(movie);
    }

    public void updateMovieById(Movie movie, Long movieId) {
        Optional<Movie> foundMovie = this.movieRepostitory.findById(movieId);
        if (foundMovie.isPresent()) {
            foundMovie.get().setId(movie.getId());
            foundMovie.get().setGenre(movie.getGenre());
            foundMovie.get().setName(movie.getName());
            foundMovie.get().setReleaseYear(movie.getReleaseYear());
            foundMovie.get().setDirector(movie.getDirector());
            foundMovie.get().setActedInActors(movie.getActedInActors());
            this.movieRepostitory.save(foundMovie.get());
        }
    }

    public void deleteMovieById(Long movieId) {
        this.movieRepostitory.deleteById(movieId);
    }
}
