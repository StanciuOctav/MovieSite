package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.UserService;
import com.movie.moviesite.model.User;
import com.movie.moviesite.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkUser(String email, String password) {
        return this.userRepository.checkUser(email, password);
    }

    public User checkUserLogin(User user) {
        return this.userRepository.findByEmail(user.getEmail());
    }


    @Override
    public Collection<User> getAllActors() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User with the id " + id + " doesn't exist");
        }
    }

    @Override
    public User createUser(User user) {
        User user1 = this.userRepository.findByEmail(user.getEmail());
        if (user1 != null) {
            return null;
        } else {
            return this.userRepository.save(user);
        }
    }

    @Override
    public User updateUser(Long userId, User user) {
        User user1 = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't retrieve user with the id " + userId));
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setName(user.getName());
        return this.userRepository.save(user1);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't retrieve user with the id " + userId));
        this.userRepository.delete(user);
    }
}
