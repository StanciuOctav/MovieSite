package com.movie.moviesite.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.List;

@NodeEntity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;

    @Relationship(type = "RATED", direction = Relationship.INCOMING)
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public User() {
    }

    public User(Long id, String name, Integer age, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.movies = movies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}