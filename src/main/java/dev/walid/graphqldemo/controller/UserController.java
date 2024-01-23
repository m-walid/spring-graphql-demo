package dev.walid.graphqldemo.controller;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping
    public User user(@Argument Long id) {
        return userService.getUserById(id);
    }

    @QueryMapping
    public PaginatedResponse<List<User>> users(@Argument PaginationRequest paginationRequest) {
        return userService.getAllUsers(paginationRequest);
    }


    @SchemaMapping(typeName = "User")
    public PaginatedResponse<List<User>> followers(User user, @Argument PaginationRequest paginationRequest) {
        return userService.getUserFollowers(user, paginationRequest);
    }

    @SchemaMapping(typeName = "User")
    public PaginatedResponse<List<User>> following(User user, @Argument PaginationRequest paginationRequest) {
        return userService.getUserFollowing(user, paginationRequest);
    }
}
