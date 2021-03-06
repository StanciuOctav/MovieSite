package com.movie.moviesite.model;

import com.movie.moviesite.relationship.Reviewed;
import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import java.util.Collection;

@Node("USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private Integer age;
    private String email;
    private String name;
    private String password;

    @Relationship(type = "REVIEWED", direction = Relationship.Direction.OUTGOING)
    private Collection<Reviewed> reviewedMovies;

    @Relationship(type = "IN_WATCHLIST", direction = Relationship.Direction.OUTGOING)
    private Collection<Movie> watchlistMovies;

}
