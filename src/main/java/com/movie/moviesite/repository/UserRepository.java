package com.movie.moviesite.repository;

import com.movie.moviesite.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByEmail(String email);

    @Query("MATCH (u:USER) WHERE u.email = $email OR u.password = $password RETURN u")
    User checkUser(String email, String password);
}
