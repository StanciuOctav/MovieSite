package com.movie.moviesite.controller;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Actor getActoryById(@PathVariable Long actorId) {
        return this.actorService.getActorById(actorId);
    }

    @PostMapping
    public String saveActor(@RequestBody Actor actor) {
        this.actorService.saveActor(actor);
        return actor.toString();
    }

    @PatchMapping ("/{actorId}")
    public String updateActor(@RequestBody Actor actor, @PathVariable Long actorId) {
        this.actorService.updateActor(actor, actorId);
        return actor.toString();
    }

    @DeleteMapping("/{actorId}")
    public String deleteActor(@PathVariable Long actorId) {
        this.actorService.deleteActor(actorId);
        return "ACTOR DELETED";
    }
}
