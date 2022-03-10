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

    public void saveDirector(Director director) {
        this.directorRepository.save(director);
    }

    public void updateDirector(Director director, Long dirId) {
        Director oldDir = this.directorRepository.getById(dirId);
        oldDir.setId(director.getId());
        oldDir.setName(director.getName());
        oldDir.setAge(director.getAge());
        oldDir.setBornIn(director.getBornIn());
        oldDir.setNetWorth(director.getNetWorth());
        this.directorRepository.save(oldDir);
    }

}

