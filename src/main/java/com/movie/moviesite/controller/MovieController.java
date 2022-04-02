package com.movie.moviesite.controller;

import com.movie.moviesite.dto.MovieDTO;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.service.MovieServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieServiceImpl movieService;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieController(MovieServiceImpl movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Collection<MovieDTO> getAllMovies() {
        return this.movieService.getAllMovies()
                .stream()
                .map((movie -> modelMapper.map(movie, MovieDTO.class)))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        Movie movie = this.movieService.getMovieById(id);
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movieDTO, @RequestParam("director") String directedBy) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie movie1 = this.movieService.createMovie(movie, directedBy);
        MovieDTO movieDTO1 = modelMapper.map(movie1, MovieDTO.class);
        return ResponseEntity.ok(movieDTO1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovieById(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie movie1 = this.movieService.updateMovie(id, movie);
        MovieDTO movieDTO1 = modelMapper.map(movie1, MovieDTO.class);
        return ResponseEntity.ok(movieDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id) {
        this.movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie with the id " + id + " was deleted");
    }

}
