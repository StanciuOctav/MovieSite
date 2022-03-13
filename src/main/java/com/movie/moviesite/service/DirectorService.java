package com.movie.moviesite.service;

import com.movie.moviesite.model.Director;
import com.movie.moviesite.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
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

    public Director getDirectorById(Long directorId) {
        return this.directorRepository.getDirectorById(directorId);
    }

    public void saveDirector(Director director) {
        this.directorRepository.save(director);
    }

    public void updateDirectorById(Director director, Long directorId) {
        Optional<Director> foundDir = this.directorRepository.findById(directorId);
        if (foundDir.isPresent()) {
            foundDir.get().setId(director.getId());
            foundDir.get().setName(director.getName());
            foundDir.get().setAge(director.getAge());
            foundDir.get().setBornIn(director.getBornIn());
            foundDir.get().setNetWorth(director.getNetWorth());
            this.directorRepository.save(foundDir.get());
        }
    }

    public void deleteDirectorById(Long directorId) {
        this.directorRepository.deleteById(directorId);
    }

}

