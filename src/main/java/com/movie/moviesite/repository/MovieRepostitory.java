package com.movie.moviesite.repository;

import com.movie.moviesite.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MovieRepostitory extends Neo4jRepository<Movie, Long> {

    Movie findByName(String name);

    @Query("MATCH (m:MOVIE{name:$movie}), (d:DIRECTOR{name:$directedBy})" +
            "CREATE (m)<-[:DIRECTED]-(d)" +
            "RETURN m")
    void createDirectedRel(String movie, String directedBy);
}