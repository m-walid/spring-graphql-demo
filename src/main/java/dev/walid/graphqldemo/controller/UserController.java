package dev.walid.graphqldemo.controller;

import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }
}
