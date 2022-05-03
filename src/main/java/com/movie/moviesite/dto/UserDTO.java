package com.movie.moviesite.dto;

import com.movie.moviesite.model.Movie;
import lombok.Data;

import java.util.Collection;

@Data
public class UserDTO {
    private Long id;
    private Integer age;
    private String email;
    private String name;
    private String password;

    private Collection<ReviewedDTO> reviewedMovies;
    private Collection<MovieDTO> watchlistMovies;
}
