package com.movie.moviesite.dto;

import com.movie.moviesite.model.Actor;
import lombok.Data;

import java.util.Collection;

@Data
public class MovieDTO {

    private Long id;
    private String name;
    private String genre;
    private Integer releaseYear;
    private String imageURL;
    private String description;

    private DirectorDTO director;
    private Collection<ActorDTO> actedInActors;
}
