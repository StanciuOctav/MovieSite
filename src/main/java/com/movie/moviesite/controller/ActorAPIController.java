package com.movie.moviesite.controller;

import com.movie.moviesite.dto.ActorDTO;
import com.movie.moviesite.interfaces.ActorService;
import com.movie.moviesite.model.Actor;
import com.movie.moviesite.service.ActorServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/actors")
public class ActorAPIController {

    private final ActorServiceImpl actorService;
    private final ModelMapper modelMapper;

    @Autowired
    public ActorAPIController(ActorServiceImpl actorService, ModelMapper modelMapper) {
        this.actorService = actorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Collection<ActorDTO> getAllActors() {
        return this.actorService.getAllActors()
                .stream()
                .map(actor -> modelMapper.map(actor, ActorDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable Long id) {
        Actor actor = this.actorService.getActorById(id);
        ActorDTO actorResponse = modelMapper.map(actor, ActorDTO.class);
        return ResponseEntity.ok(actorResponse);
    }

    @PostMapping
    public ResponseEntity<ActorDTO> saveActor(@Valid @RequestBody ActorDTO actorDTO) {
        Actor actorRequest = modelMapper.map(actorDTO, Actor.class);

        Actor actor = this.actorService.createActor(actorRequest);

        ActorDTO actorResponse = modelMapper.map(actor, ActorDTO.class);
        return new ResponseEntity<>(actorResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ActorDTO> updateActorById(@PathVariable Long id, @Valid @RequestBody ActorDTO actorDTO) {
        Actor actorRequest = modelMapper.map(actorDTO, Actor.class);

        Actor actor = this.actorService.updateActor(id, actorRequest);

        ActorDTO actorDTO1 = modelMapper.map(actor, ActorDTO.class);
        return ResponseEntity.ok(actorDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActorById(@PathVariable Long id) {
        this.actorService.deleteActor(id);
        return ResponseEntity.ok("Actor with the id " + id + " was deleted");
    }
}
