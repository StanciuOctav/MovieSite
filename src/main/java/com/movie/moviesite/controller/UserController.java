package com.movie.moviesite.controller;

import com.movie.moviesite.model.User;
import com.movie.moviesite.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addToWatchlist")
    public ResponseEntity<String> addMovieToWatchlist(@RequestParam("movie") String movieName) {
        return this.userService.addMovieToWatchlist(movieName);
    }

    @GetMapping("/addReview")
    public ResponseEntity<String> userAddMovieReview(@RequestParam("user") String username, @RequestParam("movie") String movieName, @RequestParam("content") String reviewContent) {
        return this.userService.userAddMovieReview(username, movieName, reviewContent);
    }

    /**
     * CRUD OPEARTIONS
     */
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        System.out.println(user);
        return this.userService.checkUserLogin(user);
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
