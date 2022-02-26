package com.movie.moviesite.service;

import com.movie.moviesite.model.Director;
import com.movie.moviesite.model.Movie;
import com.movie.moviesite.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Collection<Director> getAllDirectors() {
        return this.directorRepository.getAllDirectors();
    }

    public Collection<Director> getDirectedMovies(Long id) {
        return this.directorRepository.getDirectedMovies(id);
    }
}

