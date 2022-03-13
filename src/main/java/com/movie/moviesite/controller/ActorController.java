package com.movie.moviesite.controller;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public Collection<Actor> getAllActors(){
        return this.actorService.getAllActors();
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long actorId) {
        return new ResponseEntity<>(this.actorService.getActorById(actorId), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        this.actorService.saveActor(actor);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

    @PatchMapping ("/{actorId}")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor, @PathVariable Long actorId) {
        this.actorService.updateActor(actor, actorId);
        return new ResponseEntity<>(actor, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Long> deleteActor(@PathVariable Long actorId) {
        this.actorService.deleteActor(actorId);
        return new ResponseEntity<>(actorId, HttpStatus.OK);
    }
}
