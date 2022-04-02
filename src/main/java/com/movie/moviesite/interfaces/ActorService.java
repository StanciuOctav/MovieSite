package com.movie.moviesite.interfaces;

import com.movie.moviesite.model.Actor;

import java.util.Collection;

public interface ActorService {

    Collection<Actor> getAllActors();

    Actor getActorById(Long id);

    Actor createActor(Actor actor);

    Actor updateActor(Long actorId, Actor actor);

    void deleteActor(Long actorId);

}
