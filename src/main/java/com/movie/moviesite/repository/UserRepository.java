package com.movie.moviesite.repository;

import com.movie.moviesite.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

// TODO: update these queries so that they execute on the logged in User
public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByEmail(String email);

    @Query("MATCH (u:USER), (m1:MOVIE), (m2:MOVIE)" +
            "MATCH (u)-[iw:IN_WATCHLIST]->(m1)" +
            "MATCH (u)-[r:REVIEWED]->(m2)" +
            "RETURN u, collect(iw), collect(m1), collect(r), collect(m2)")
    Collection<User> getAllUsers();

    @Query("MATCH (u:USER) WHERE u.email = $email OR u.password = $password RETURN u")
    User checkUser(String email, String password);
}
