package com.movie.moviesite.interfaces;

import com.movie.moviesite.model.Director;

import java.util.Collection;

public interface DirectorService {
    Collection<Director> getAllDirectors();

    Director getDirectorById(Long id);

    Director createDirector(Director director);

    Director updateDirector(Long id, Director director);

    void deleteDirector(Long id);
}
