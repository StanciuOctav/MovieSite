package com.movie.moviesite.model;

import com.movie.moviesite.annotations.AgeAnnotaion;
import com.movie.moviesite.relationship.Reviewed;
import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Node("USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @AgeAnnotaion
    private Integer age;
    @Email(message = "Wrong email format")
    private String email;
    @NotBlank(message = "The User's name cannot be null or empty")
    private String name;
    @NotBlank(message = "The User's password cannot be null or empty")
    private String password; // TODO: create an annotation that verifies password for more details

    @Relationship(type = "REVIEWED", direction = Relationship.Direction.OUTGOING)
    private Collection<Reviewed> reviewedMovies;

    @Relationship(type = "IN_WATCHLIST", direction = Relationship.Direction.OUTGOING)
    private Collection<Movie> watchlistMovies;

}
