package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.DirectorService;
import com.movie.moviesite.model.Director;
import com.movie.moviesite.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository) {
        super();
        this.directorRepository = directorRepository;
    }


    @Override
    public Collection<Director> getAllDirectors() {
        return this.directorRepository.getAllDirectors();
    }

    @Override
    public Director getDirectorById(Long id) {
        Optional<Director> director = this.directorRepository.findById(id);
        if (director.isPresent()) {
            return director.get();
        } else {
            throw new ResourceNotFoundException("Director with the id " + id + " doesn't exist");
        }
    }

    @Override
    public Director createDirector(Director director) {
        Director director1 = this.directorRepository.findByName(director.getName());
        if (director1 != null) {
            return null;
        } else {
            return this.directorRepository.save(director);
        }
    }

    @Override
    public Director updateDirector(Long id, Director director) {
        Director director1 = this.directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director with the id " + id + " doesn't exist"));
        director1.setNetWorth(director.getNetWorth());
        director1.setAge(director.getAge());
        director1.setName(director.getName());
        director1.setBornIn(director.getBornIn());
        return this.directorRepository.save(director1);
    }

    @Override
    public void deleteDirector(Long id) {
        Director director = this.directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director with the id " + id + " doesn't exist"));
        this.directorRepository.delete(director);
    }
}

