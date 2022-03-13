package com.movie.moviesite.model;

import com.movie.moviesite.annotations.AgeAnnotaion;
import com.movie.moviesite.annotations.BornInAnnotation;
import com.movie.moviesite.annotations.NetWorthAnnotation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.validation.constraints.*;
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
    @NotBlank(message = "The actor's name may not be null or empty")
    private String name;
    @AgeAnnotaion
    private Integer age;
    @BornInAnnotation
    private String bornIn;
    @NetWorthAnnotation
    private Long netWorth;

    @Relationship(type = "ACTED_IN")
    private Collection<Movie> moviesActedIn;
}
