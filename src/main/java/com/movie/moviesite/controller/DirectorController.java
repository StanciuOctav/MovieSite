package com.movie.moviesite.controller;

import com.movie.moviesite.model.Director;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/directors")
public class DirectorController {
    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public Collection<Director> getAllDirectors() {
        return this.directorService.getAllDirectors();
    }

    @GetMapping("/directed/{id}")
    public Collection<Director> getDirectedMovies(@PathVariable("id") Long id) {
        return this.directorService.getDirectedMovies(id);
    }
}
