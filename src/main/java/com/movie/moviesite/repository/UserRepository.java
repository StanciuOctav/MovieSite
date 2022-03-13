package com.movie.moviesite.repository;

import com.movie.moviesite.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u:USER), (m1:MOVIE), (m2:MOVIE)" +
            "MATCH (u)-[iw:IN_WATCHLIST]->(m1)" +
            "MATCH (u)-[r:REVIEWED]->(m2)" +
            "RETURN u, collect(iw), collect(m1), collect(r), collect(m2)")
    Collection<User> getAllUsers();

    @Query("MATCH (u:USER)-[r:REVIEWED]->(m:MOVIE) WHERE ID(u) = $userId RETURN u, collect(r), collect(m)")
    User getUserById(Long userId);
}
