package com.movie.moviesite.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.validation.constraints.*;
import java.util.Collection;

@Node("DIRECTOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Director {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String bornIn;
    private Long netWorth;
    private String imageURL;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.OUTGOING)
    private Collection<Movie> directedMovies;
}
