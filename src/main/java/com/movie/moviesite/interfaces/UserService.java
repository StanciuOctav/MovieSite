package com.movie.moviesite.interfaces;

import com.movie.moviesite.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getAllActors();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);
}
