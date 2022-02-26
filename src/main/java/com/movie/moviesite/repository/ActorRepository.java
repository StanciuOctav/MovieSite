package com.movie.moviesite.repository;

import com.movie.moviesite.model.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface ActorRepository extends Neo4jRepository<Actor, Long> {

    @Query("MATCH (a:ACTOR)-[ai:ACTED_IN]->(m:MOVIE) RETURN a, collect(ai), collect(m)")
    Collection<Actor> getAllActors();
}
