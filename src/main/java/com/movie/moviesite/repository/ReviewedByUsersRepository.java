package com.movie.moviesite.repository;

import com.movie.moviesite.relationship.ReviewedByUser;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewedByUsersRepository extends Neo4jRepository<ReviewedByUser, Long> {


}
