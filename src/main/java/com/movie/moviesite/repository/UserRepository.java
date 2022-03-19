package com.movie.moviesite.repository;

import com.movie.moviesite.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

// TODO: update these queries so that they execute on the logged in User
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u:USER), (m1:MOVIE), (m2:MOVIE)" +
            "MATCH (u)-[iw:IN_WATCHLIST]->(m1)" +
            "MATCH (u)-[r:REVIEWED]->(m2)" +
            "RETURN u, collect(iw), collect(m1), collect(r), collect(m2)")
    Collection<User> getAllUsers();

    @Query("MATCH (u:USER)-[r:REVIEWED]->(m:MOVIE) WHERE ID(u) = $userId RETURN u, collect(r), collect(m)")
    User getUserById(Long userId);

    @Query("MATCH (u:USER) WHERE u.email = $email OR u.password = $password RETURN u")
    User checkUser(String email, String password);

    @Query("MATCH (u:USER {name: $username})-[r:REVIEWED]->(m:MOVIE {name: $movieName}) RETURN u, collect(r), collect(m)")
    User getReview(String username, String movieName);

    @Query("MATCH (u:USER {name: $username}), (m:MOVIE {name: $movieName}) CREATE (u)-[:REVIEWED {content: $reviewContent}]->(m)")
    void addReview(String username, String movieName, String reviewContent);

    @Query("MATCH (u:USER {name: $username})-[r:REVIEWED]->(m:MOVIE {name: $movieName}) SET r.content = $reviewContent")
    void updateReview(String username, String movieName, String reviewContent);

    @Query("MATCH (u:USER)-[iw:IN_WATCHLIST]->(m:MOVIE {name: $movieName}) RETURN u, collect(iw), collect(m)")
    User checkMovieInWatchlist(String movieName);

    @Query("MATCH (u:USER), (m:MOVIE {name: $movieName}) CREATE (u)-[:IN_WATCHLIST]->(m)")
    void addMovieToWatchlist(String movieName);
}
