package com.movie.moviesite.controller;

import com.movie.moviesite.model.User;
import com.movie.moviesite.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO: being able to add movies to its watchlist
    // TODO: being able to add a review to a movie or update its review (do it with queryString in url @RequestParam
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@Valid @RequestBody User user, @PathVariable Long userId) {
        this.userService.updateUserById(user, userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
        return ResponseEntity.ok("Deleted user with the id " + userId + "\n");
    }

}
