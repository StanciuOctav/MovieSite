package com.movie.moviesite.dto;

import com.movie.moviesite.relationshipDTO.ReviewedDTO;
import lombok.Data;

import java.util.Collection;

@Data
public class UserDTO {
    private Long id;
    private Integer age;
    private String email;
    private String name;
    private String password;

}
