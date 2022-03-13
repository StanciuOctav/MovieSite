package com.movie.moviesite.service;

import com.movie.moviesite.model.Actor;
import com.movie.moviesite.model.User;
import com.movie.moviesite.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    public User getUserById(Long userId) {
        return this.userRepository.getUserById(userId);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void updateUserById(User user, Long userId) {
        Optional<User> foundUser = this.userRepository.findById(userId);
        if (foundUser.isPresent()) {
            foundUser.get().setAge(user.getAge());
            foundUser.get().setEmail(user.getEmail());
            foundUser.get().setName(user.getName());
            foundUser.get().setPassword(user.getPassword());
            this.userRepository.save(foundUser.get());
        }
    }

    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(userId);
    }

}
