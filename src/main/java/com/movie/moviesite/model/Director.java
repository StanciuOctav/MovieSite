package com.movie.moviesite.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;

@Node("DIRECTOR")
public class Director {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String bornIn;
    private Long netWorth;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.OUTGOING)
    private Collection<Movie> movies;

    public Director() {}

    public Director(Long id, String name, Integer age, String bornIn, Long netWorth, Collection<Movie> movies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bornIn = bornIn;
        this.netWorth = netWorth;
        this.movies = movies;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBornIn() {
        return bornIn;
    }

    public void setBornIn(String bornIn) {
        this.bornIn = bornIn;
    }

    public Long getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Long netWorth) {
        this.netWorth = netWorth;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }
}
