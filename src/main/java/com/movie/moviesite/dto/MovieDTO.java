package com.movie.moviesite.dto;

import lombok.Data;

@Data
public class MovieDTO {

    private Long id;
    private String name;
    private String genre;
    private Integer releaseYear;
    private String imageURL;
    private String description;

    private DirectorDTO director;
}
