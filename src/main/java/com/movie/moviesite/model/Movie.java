package com.movie.moviesite.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;

@Node("MOVIE")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String genre;
    private Integer releaseYear;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private Director director;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private Collection<Actor> actors;

    public Movie() {}

    public Movie(Long id, String name, String genre, Integer releaseYear, Director director, Collection<Actor> actors) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Collection<Actor> getActors() {
        return actors;
    }

    public void setActors(Collection<Actor> actors) {
        this.actors = actors;
    }
}
