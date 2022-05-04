package com.movie.moviesite.repository;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

/**
 * Using custom @Query and dynamic @Query so it doesn't have a recursive call (infinite loop to populate lists)
 */

public interface ActorRepository extends Neo4jRepository<Actor, Long> {

    @Query("MATCH (a:ACTOR)-[ai:ACTED_IN]->(m:MOVIE) RETURN a, collect(ai), collect(m)")
    Collection<Actor> getAllActors();

    Actor findByName(String name);

    @Query("MATCH (a:ACTOR)-[:ACTED_IN]->(m:MOVIE) WHERE ID(m) = $movieId RETURN a")
    Collection<Actor> getActedInActors(Long movieId);
}
