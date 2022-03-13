package com.movie.moviesite.controller;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Collection<Actor>> getAllActors() {
        return ResponseEntity.ok(this.actorService.getAllActors());
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long actorId) {
        return ResponseEntity.ok(this.actorService.getActorById(actorId));
    }

    @PostMapping
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        this.actorService.saveActor(actor);
        return ResponseEntity.created(URI.create("/actors")).body(actor);
    }

    @PatchMapping("/{actorId}")
    public ResponseEntity<Actor> updateActorById(@RequestBody Actor actor, @PathVariable Long actorId) {
        this.actorService.updateActorById(actor, actorId);
        return ResponseEntity.ok(actor);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<String> deleteActorById(@PathVariable Long actorId) {
        this.actorService.deleteActorById(actorId);
        return ResponseEntity.ok("Deleted actor with the id " + actorId + "\n");
    }
}
