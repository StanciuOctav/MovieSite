package com.movie.moviesite.repository;

import com.movie.moviesite.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MovieRepostitory extends Neo4jRepository<Movie, Long> {

    @Query("MATCH (m:MOVIE)<-[ai:ACTED_IN]-(a:ACTOR)\n" +
            "MATCH (m)<-[di:DIRECTED]-(d:DIRECTOR)\n" +
            "RETURN m, collect(ai), collect(a), collect(di), collect(d)")
    Collection<Movie> getAllMovies();

    @Query("MATCH (m:MOVIE)<-[ai:ACTED_IN]-(a:ACTOR)\n" +
            "MATCH (m)<-[di:DIRECTED]-(d:DIRECTOR)\n" +
            "WHERE ID(m) = $movieId\n" +
            "RETURN m, collect(ai), collect(a), collect(di), collect(d)")
    Movie getMovieById(Long movieId);

    @Query("MATCH (m:MOVIE)<-[r:REVIEWED]-(u:USER) RETURN m, collect(r), collect(u)")
    Collection<Movie> getReviewedMovies();
}
