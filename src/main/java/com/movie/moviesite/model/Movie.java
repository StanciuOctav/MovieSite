package com.movie.moviesite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.moviesite.annotations.ReleaseYearAnnotation;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;

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
    @NotBlank(message = "Movie's name cannot be null or blank")
    private String name;
    @NotBlank(message = "Movie's genre cannot be null or blank")
    private String genre;
    @ReleaseYearAnnotation
    private Integer releaseYear;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private Director director;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private Collection<Actor> actedInActors;

    @Relationship(type = "IN_WATCHLIST", direction = Relationship.Direction.INCOMING)
    private Collection<User> watchListUsers;

    /**
     * For now, not a reason to have a User object in Movie
     */
    @JsonIgnore
    @Relationship(type = "REVIEWED", direction = Relationship.Direction.INCOMING)
    private User reviewer;
}
