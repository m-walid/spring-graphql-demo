package dev.walid.graphqldemo.controller;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.dto.PostInput;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @QueryMapping
    public Post post(@Argument Long id) {
        return postService.getPostById(id);
    }

    @QueryMapping
    public PaginatedResponse<List<Post>> posts(@Argument PaginationRequest paginationRequest) {
        return postService.getAllPosts(paginationRequest);
    }

    @MutationMapping
    public Post createPost(@Argument PostInput postInput) {
        return postService.createPost(postInput);
    }

    @SchemaMapping(typeName="User")
    public PaginatedResponse<List<Post>> posts(User user, @Argument PaginationRequest paginationRequest) {
        return postService.getAllPostsByUser(user, paginationRequest);
    }
}

