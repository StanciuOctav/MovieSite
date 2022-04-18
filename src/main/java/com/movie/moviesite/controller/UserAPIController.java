package com.movie.moviesite.controller;

import com.movie.moviesite.dto.UserDTO;
import com.movie.moviesite.model.User;
import com.movie.moviesite.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserAPIController {

    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    public UserAPIController(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Collection<UserDTO> getAllUsers() {
        return this.userService.getAllActors()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = this.userService.getUserById(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = this.userService.createUser(userRequest);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/user")
    public ResponseEntity<?> userLogin(@RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User user1 = this.userService.checkUserLogin(user);
        UserDTO userDTO1 = modelMapper.map(user1, UserDTO.class);
        return userDTO1 != null ? ResponseEntity.ok(userDTO1) : ResponseEntity.status(404).body("User not found");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = this.userService.updateUser(id, userRequest);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User with the id " + id + " was deleted");
    }

}
