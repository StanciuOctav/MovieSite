package com.movie.moviesite.repository;

import com.movie.moviesite.model.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface ActorRepository extends Neo4jRepository<Actor, Long> {

    @Query("MATCH (a:ACTOR) RETURN a")
    Collection<Actor> getAllActors();
}
