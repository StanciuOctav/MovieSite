package com.movie.moviesite.service;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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

    public Actor getActorById(Long actorId) {
        Optional<Actor> actor = this.actorRepository.findById(actorId);
        return actor.orElseGet(Actor::new);
    }

    public void saveActor(Actor actor) {
        this.actorRepository.save(actor);
    }

    public void updateActor(Actor actor, Long actorId) {
        Optional<Actor> foundActor = this.actorRepository.findById(actorId);
        if(foundActor.isPresent()) {
            foundActor.get().setName(actor.getName());
            foundActor.get().setAge(actor.getAge());
            foundActor.get().setBornIn(actor.getBornIn());
            foundActor.get().setNetWorth(actor.getNetWorth());
            this.actorRepository.save(foundActor.get());
        }
    }

    public void deleteActor(Long actorId) {
        actorRepository.deleteById(actorId);
    }
}
