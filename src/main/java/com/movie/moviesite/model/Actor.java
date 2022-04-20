package com.movie.moviesite.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;

@Node("ACTOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Actor {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String bornIn;
    private Long netWorth;
    private String imageURL;

    @Relationship(type = "ACTED_IN")
    private Collection<Movie> moviesActedIn;
}
