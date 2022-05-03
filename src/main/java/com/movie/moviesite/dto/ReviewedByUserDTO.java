package com.movie.moviesite.dto;

import lombok.Data;

@Data
public class ReviewedByUserDTO {
    private Long id;
    private UserDTO userDTO;
    String content;
}
