package com.movie.moviesite.service;

import com.movie.moviesite.model.User;
import com.movie.moviesite.relationship.Reviewed;
import com.movie.moviesite.repository.UserRepository;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> getReview(String username, String movieName, String content) {
        User u = this.userRepository.getReview(username, movieName);
        if (u == null) {
            this.userRepository.addReview(username, movieName, content);
            return ResponseEntity.ok("Added the review for the movie " + movieName + " with the content " + content);
        } else {
            this.userRepository.updateReview(username, movieName, content);
            return ResponseEntity.ok("Updated the review content: " + content);
        }
    }

    /**
     * CRUD OPERATIONS
     */
    public Collection<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    public User getUserById(Long userId) {
        return this.userRepository.getUserById(userId);
    }

    public ResponseEntity<?> saveUser(User user) {
        User oldUser = this.userRepository.checkUser(user.getEmail(), user.getPassword());
        if (oldUser == null) {
            this.userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(409).body("There already is an user with the same email or password");
        }
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
