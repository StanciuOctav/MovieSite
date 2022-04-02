package com.movie.moviesite.dto;

import lombok.Data;

@Data
public class DirectorDTO {
    private Long id;
    private String name;
    private Integer age;
    private String bornIn;
    private Long netWorth;
}
