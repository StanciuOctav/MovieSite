package com.movie.moviesite.dto;

import com.movie.moviesite.model.Movie;
import lombok.Data;

import java.util.Collection;

@Data
public class ActorDTO {
    private Long id;
    private String name;
    private Integer age;
    private String bornIn;
    private Long netWorth;

    //private Collection<Movie> movies;
}
