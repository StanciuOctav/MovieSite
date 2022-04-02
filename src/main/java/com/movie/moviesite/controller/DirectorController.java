package com.movie.moviesite.controller;

import com.movie.moviesite.dto.DirectorDTO;
import com.movie.moviesite.model.Director;
import com.movie.moviesite.service.DirectorServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorServiceImpl directorService;
    private final ModelMapper modelMapper;

    @Autowired
    public DirectorController(DirectorServiceImpl directorService, ModelMapper modelMapper) {
        this.directorService = directorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Collection<DirectorDTO> getAllDirectors() {
        return this.directorService.getAllDirectors()
                .stream()
                .map(director -> modelMapper.map(director, DirectorDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
        Director director = this.directorService.getDirectorById(id);
        DirectorDTO directorDTO = this.modelMapper.map(director, DirectorDTO.class);
        return ResponseEntity.ok(directorDTO);
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> saveDirector(@Valid @RequestBody DirectorDTO directorDTO) {
        Director directorRequest = this.modelMapper.map(directorDTO, Director.class);
        Director director = this.directorService.createDirector(directorRequest);
        DirectorDTO directorResponse = this.modelMapper.map(director, DirectorDTO.class);
        return ResponseEntity.status(201).body(directorResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirectorById(@PathVariable Long id, @Valid @RequestBody DirectorDTO directorDTO) {
        Director director = modelMapper.map(directorDTO, Director.class);
        Director director1 = this.directorService.updateDirector(id, director);
        DirectorDTO directorDTO1 = modelMapper.map(director1, DirectorDTO.class);
        return ResponseEntity.ok(directorDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirectorById(@PathVariable Long id) {
        this.directorService.deleteDirector(id);
        return ResponseEntity.ok("Director with the id " + id + " was deleted");
    }
}
