package com.movie.moviesite.relationship;

import com.movie.moviesite.model.Movie;
import lombok.*;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.*;

@Data
@RelationshipProperties
public class Reviewed {
    @RelationshipId
    private Long id;
    @TargetNode
    private Movie movie;
    String content;
}
