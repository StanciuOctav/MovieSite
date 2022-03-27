package com.movie.moviesite.service;

import com.movie.moviesite.model.Movie;
import com.movie.moviesite.repository.MovieRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Movie getMovieById(Long movieId) {
        return this.movieRepostitory.getSimpleMovieById(movieId);
    }

    public ResponseEntity<?> saveMovie(Movie movie, String directedBy) {
        Movie oldMovie = this.movieRepostitory.getMovieByName(movie.getName());
        if (oldMovie == null) {
            this.movieRepostitory.save(movie);
            this.movieRepostitory.createDirectedRel(movie.getName(), directedBy);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.status(409).body("This movies already exists");
        }
    }

    public ResponseEntity<?> updateMovieById(Movie movie, Long movieId) {
        Optional<Movie> foundMovie = this.movieRepostitory.findById(movieId);
        if (foundMovie.isPresent()) {
            foundMovie.get().setId(movie.getId());
            foundMovie.get().setGenre(movie.getGenre());
            foundMovie.get().setName(movie.getName());
            foundMovie.get().setReleaseYear(movie.getReleaseYear());
            foundMovie.get().setDirector(movie.getDirector());
            this.movieRepostitory.save(foundMovie.get());
            return ResponseEntity.ok().body("The movie \"" + movie.getName() + "\" was updated");
        } else {
            return ResponseEntity.status(404).body("Movie not found");
        }
    }

    public ResponseEntity<?> deleteMovieById(Long movieId) {
        Movie m = this.movieRepostitory.getSimpleMovieById(movieId);
        if (m == null) {
            return ResponseEntity.status(404).body("Movie doesn't exist with the id " + movieId);
        } else {
            this.movieRepostitory.deleteById(movieId);
            return ResponseEntity.ok().body("Deleted successfully");
        }
    }
}
