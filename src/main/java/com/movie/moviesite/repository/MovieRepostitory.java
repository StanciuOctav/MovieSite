package com.movie.moviesite.repository;

import com.movie.moviesite.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface MovieRepostitory extends Neo4jRepository<Movie, Long> {

    @Query("MATCH (m:MOVIE) RETURN m")
    Collection<Movie> getAllMovies();

    @Query("MATCH (d:DIRECTOR) -[dir:DIRECTED]-> (m:MOVIE) WHERE ID(d) = $id RETURN DISTINCT m")
    Collection<Movie> getMoviesDirectedBy(Long id);
}
