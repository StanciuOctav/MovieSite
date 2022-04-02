package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.MovieService;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.repository.MovieRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
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

    @Override
    public Movie createMovie(Movie movie, String directedBy) {
        Movie movie1 = this.movieRepostitory.findByName(movie.getName());
        if (movie1 != null) {
            return null;
        } else {
            this.movieRepostitory.save(movie);
            this.movieRepostitory.createDirectedRel(movie.getName(), directedBy);
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
        // TODO: also update the director
        return this.movieRepostitory.save(movie1);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = this.movieRepostitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with the id " + id + " doesn't exist"));
        this.movieRepostitory.delete(movie);
    }
}
