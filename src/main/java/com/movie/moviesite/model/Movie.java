package com.movie.moviesite.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@NodeEntity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String director;

    public Movie() {
    }

    public Movie(Long id, String title, String director) {
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
}
