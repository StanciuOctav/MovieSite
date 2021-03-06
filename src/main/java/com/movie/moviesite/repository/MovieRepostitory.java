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

    @Query("MATCH (m:MOVIE)<-[r:REVIEWED]-(u:USER) WHERE ID(m) = $movieId RETURN m, collect(r), collect(u)")
    Movie getMovieReview(Long movieId);

    @Query("MATCH (d:DIRECTOR)-[di:DIRECTED]->(m:MOVIE) WHERE ID(d)=$directorId RETURN m, collect(di), collect(d)")
    Collection<Movie> getDirectedMovies(Long directorId);

    @Query("MATCH (m:MOVIE)<-[ai:ACTED_IN]-(a:ACTOR) WHERE ID(a) = $actorId RETURN m, collect(ai), collect(a)")
    Collection<Movie> getMoviesActedIn(Long actorId);

    @Query("OPTIONAL MATCH (m:MOVIE)<-[r:REVIEWED]-(u:USER {email: $email}) RETURN m, collect(r), collect(u)")
    Collection<Movie> getReviewedMovies(String email);
}