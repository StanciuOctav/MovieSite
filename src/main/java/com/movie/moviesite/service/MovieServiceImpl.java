package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.MovieService;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.repository.MovieRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepostitory movieRepostitory;

    @Autowired
    public MovieServiceImpl(MovieRepostitory movieRepostitory) {
        super();
        this.movieRepostitory = movieRepostitory;
    }

    public Collection<Movie> getDirectedMovies(Long directorId) {
        return this.movieRepostitory.getDirectedMovies(directorId);
    }

    @Override
    public Collection<Movie> getAllMovies() {
        return this.movieRepostitory.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> movie = this.movieRepostitory.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new ResourceNotFoundException("Movie with the id " + id + " doesn't exist");
        }
    }

    public Movie getMovieReview(Long movieId) {
        try {
            return this.movieRepostitory.getMovieReview(movieId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Movie createMovie(Movie movie) {
        Movie movie1 = this.movieRepostitory.findByName(movie.getName());
        if (movie1 != null) {
            return null;
        } else {
            this.movieRepostitory.save(movie);
            return movie;
        }
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie movie1 = this.movieRepostitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with the id " + id + " doesn't exist"));
        movie1.setGenre(movie.getGenre());
        movie1.setReleaseYear(movie.getReleaseYear());
        movie1.setName(movie.getName());
        movie1.setDirector(movie.getDirector());
        movie1.setDescription(movie.getDescription());
        movie1.setImageURL(movie.getImageURL());
        return this.movieRepostitory.save(movie1);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = this.movieRepostitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with the id " + id + " doesn't exist"));
        this.movieRepostitory.delete(movie);
    }
}
