package com.movie.moviesite.relationship;

import com.movie.moviesite.model.User;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
public class ReviewedByUser {
    @RelationshipId
    private Long id;
    @TargetNode
    private User User;
    String content;
}
