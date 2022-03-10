package com.movie.moviesite.controller;

import com.movie.moviesite.model.Director;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/directed")
    public Collection<Director> getDirectedMovies(@RequestParam("id") Long id) {
        return this.directorService.getDirectedMovies(id);
    }

    @PostMapping
    public String saveDirector(@RequestBody Director director) {
        this.directorService.saveDirector(director);
        return "DIRECTOR SAVED";
    }

    @PatchMapping("/{dirId}")
    public String updateDirector(@RequestBody Director director, @PathVariable Long dirId) {
        this.directorService.updateDirector(director, dirId);
        return "DIRECTOR UPDATED";
    }

}
