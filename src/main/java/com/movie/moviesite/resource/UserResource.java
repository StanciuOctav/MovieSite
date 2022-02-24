package com.movie.moviesite.resource;

import com.movie.moviesite.model.User;
import com.movie.moviesite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/user")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();
    }

}
