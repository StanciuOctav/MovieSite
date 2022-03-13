package com.movie.moviesite.controller;

import com.movie.moviesite.model.Director;
import com.movie.moviesite.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<Collection<Director>> getAllDirectors() {
        Collection<Director> directors = this.directorService.getAllDirectors();
        return ResponseEntity.ok().body(directors);
    }

    @GetMapping("/{directorId}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long directorId) {
        Director director = this.directorService.getDirectorById(directorId);
        return ResponseEntity.ok(director);
    }

    @PostMapping
    public ResponseEntity<Director> saveDirector(@Valid @RequestBody Director director) {
        this.directorService.saveDirector(director);
        return ResponseEntity.created(URI.create("/directors")).body(director);
    }

    @PatchMapping("/{directorId}")
    public ResponseEntity<Director> updateDirectorById(@Valid @RequestBody Director director, @PathVariable Long directorId) {
        this.directorService.updateDirectorById(director, directorId);
        return ResponseEntity.ok(director);
    }

    @DeleteMapping("/{directorId}")
    public ResponseEntity<String> deleteDirectorById(@PathVariable Long directorId) {
        this.directorService.deleteDirectorById(directorId);
        return ResponseEntity.ok("Deleted director with the id " + directorId + "\n");
    }
}
