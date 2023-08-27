package dev.walid.graphqldemo.service;

import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;

import java.util.List;
import java.util.Map;

public interface PostService {
    Map<User, List<Post>> getPostsForUsers(List<User> users);

    List<Post> getAllPosts();
}
