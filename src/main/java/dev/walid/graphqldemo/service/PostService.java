package dev.walid.graphqldemo.service;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.dto.PostInput;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;

import java.util.List;

public interface PostService {
    PaginatedResponse<List<Post>> getAllPosts(PaginationRequest paginationRequest);

    PaginatedResponse<List<Post>> getAllPostsByUser(User user, PaginationRequest paginationRequest);

    Post getPostById(Long id);

    Post createPost(PostInput postInput);
}
