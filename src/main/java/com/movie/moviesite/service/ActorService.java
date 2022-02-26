package com.movie.moviesite.service;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Collection<Actor> getAllActors() {
        return this.actorRepository.getAllActors();
    }
}
