package com.movie.moviesite.repository;

import com.movie.moviesite.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MovieRepostitory extends Neo4jRepository<Movie, Long> {

    @Query("MATCH (m:MOVIE)<-[di:DIRECTED]-(d:DIRECTOR)\n" +
            "RETURN m, collect(di), collect(d)")
        /*@Query("MATCH (m:MOVIE) return m")*/
    Collection<Movie> getAllMovies();

    @Query("MATCH (m:MOVIE {name: $movieName}) return m")
    Movie getMovieByName(String movieName);

    @Query("MATCH (m:MOVIE) WHERE ID(m) = $movieId " +
            "MATCH (m)<-[di:DIRECTED]-(d:DIRECTOR)" +
            "RETURN m, collect(di), collect(d)")
    Movie getSimpleMovieById(Long movieId);

    @Query("MATCH (m:MOVIE{name:$movie}), (d:DIRECTOR{name:$directedBy})" +
            "CREATE (m)<-[:DIRECTED]-(d)" +
            "RETURN m")
    void createDirectedRel(String movie, String directedBy);

    @Query("MATCH (m:MOVIE)<-[ai:ACTED_IN]-(a:ACTOR)\n" +
            "MATCH (m)<-[di:DIRECTED]-(d:DIRECTOR)\n" +
            "WHERE ID(m) = $movieId\n" +
            "RETURN m, collect(ai), collect(a), collect(di), collect(d)")
    Movie getMovieById(Long movieId);
}
