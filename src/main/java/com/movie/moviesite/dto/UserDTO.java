package com.movie.moviesite.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private Integer age;
    private String email;
    private String name;
    private String password;

}
