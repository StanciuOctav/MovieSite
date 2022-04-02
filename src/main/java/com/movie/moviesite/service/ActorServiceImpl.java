package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.ActorService;
import com.movie.moviesite.model.Actor;
import com.movie.moviesite.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        super();
        this.actorRepository = actorRepository;
    }

    @Override
    public Collection<Actor> getAllActors() {
        return this.actorRepository.getAllActors();
    }

    @Override
    public Actor getActorById(Long id) {
        Optional<Actor> actor = this.actorRepository.findById(id);
        if (actor.isPresent()) {
            return actor.get();
        } else {
            throw new ResourceNotFoundException("Actor with the id " + id + " doesn't exist");
        }
    }

    @Override
    public Actor createActor(Actor actor) {
        Actor actor1 = this.actorRepository.findByName(actor.getName());
        if (actor1 != null) {
            return null;
        } else {
            return this.actorRepository.save(actor);
        }
    }

    @Override
    public Actor updateActor(Long actorId, Actor actorRequest) {
        Actor actor = this.actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't retrieve actor with the id " + actorId));
        actor.setNetWorth(actorRequest.getNetWorth());
        actor.setAge(actorRequest.getAge());
        actor.setName(actorRequest.getName());
        actor.setBornIn(actorRequest.getBornIn());
        return this.actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long actorId) {
        Actor actor = this.actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't retrieve actor with the id " + actorId));
        this.actorRepository.delete(actor);
    }
}
