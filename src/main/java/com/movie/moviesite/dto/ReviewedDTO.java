package com.movie.moviesite.dto;

import com.movie.moviesite.model.Movie;
import lombok.Data;

@Data
public class ReviewedDTO {
    private Long id;
    private MovieDTO movieDTO;
    String content;
}
