package com.movie.moviesite.relationshipDTO;

import com.movie.moviesite.dto.UserDTO;
import lombok.Data;

@Data
public class ReviewedByUserDTO {
    private Long id;
    private UserDTO userDTO;
    String content;
}
