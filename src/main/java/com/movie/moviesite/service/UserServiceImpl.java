package com.movie.moviesite.service;

import com.movie.moviesite.interfaces.UserService;
import com.movie.moviesite.model.User;
import com.movie.moviesite.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User checkUser(String email, String password) {
        return this.userRepository.checkUser(email, password);
    }

    public ResponseEntity<?> checkUserLogin(User user) {
        User oldUser = this.userRepository.checkUser(user.getEmail(), user.getPassword());
        if (oldUser == null) {
            return ResponseEntity.status(409).body("There is no user with these fields in DB");
        } else {
            return ResponseEntity.ok(user);
        }
    }


    @Override
    public Collection<User> getAllActors() {
        return this.userRepository.getAllUsers();
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
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
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
