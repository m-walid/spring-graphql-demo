package dev.walid.graphqldemo.controller;

import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @QueryMapping
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @BatchMapping
    public Map<User, List<Post>> posts(List<User> users) {
        return postService.getPostsForUsers(users);
    }
}

