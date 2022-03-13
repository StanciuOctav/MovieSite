package com.movie.moviesite.service;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.repository.ActorRepository;
import com.movie.moviesite.validate.ValidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service
public class ActorService {

    private final ActorRepository actorRepository;
    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Collection<Actor> getAllActors() {
        Collection<Actor> actors = this.actorRepository.getAllActors();
        return ValidateRepo.isNotEmptyOrNull(actors) ? actors : null;
    }

    public Actor getActorById(Long actorId) {
        return this.actorRepository.getActorById(actorId);
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
