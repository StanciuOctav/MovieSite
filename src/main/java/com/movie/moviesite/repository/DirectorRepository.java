package com.movie.moviesite.repository;

import com.movie.moviesite.model.Director;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface DirectorRepository extends Neo4jRepository<Director, Long> {

    @Query("MATCH (d:DIRECTOR)-[dir:DIRECTED]->(m:MOVIE) RETURN d, collect(dir), collect(m)")
    Collection<Director> getAllDirectors();

    @Query("MATCH (d:DIRECTOR)-[dir:DIRECTED]->(m:MOVIE) WHERE ID(d) = $directorId RETURN d, collect(dir), collect(m)")
    Director getDirectorById(Long directorId);
}
