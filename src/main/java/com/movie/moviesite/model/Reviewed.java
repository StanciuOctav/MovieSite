package com.movie.moviesite.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RelationshipProperties
public class Reviewed {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @TargetNode
    private Movie movie;
}
