package com.movie.moviesite.relationshipDTO;

import com.movie.moviesite.dto.MovieDTO;
import lombok.Data;

@Data
public class ReviewedDTO {
    private Long id;
    private MovieDTO movieDTO;
    String content;
}
