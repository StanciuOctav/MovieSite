package com.movie.moviesite.model;

import com.movie.moviesite.relationship.ReviewedByUser;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;
import java.util.List;

@Node("MOVIE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String genre;
    private Integer releaseYear;
    private String imageURL;
    private String description;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private Director director;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private Collection<Actor> actedInActors;

    @Relationship(type = "REVIEWED", direction = Relationship.Direction.INCOMING)
    private List<ReviewedByUser> reviewedByUsers;
}
