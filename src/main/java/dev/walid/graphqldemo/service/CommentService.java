package dev.walid.graphqldemo.service;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.model.Comment;
import dev.walid.graphqldemo.model.Post;

import java.util.List;

public interface CommentService {
    PaginatedResponse<List<Comment>> getPostComments(Post post, PaginationRequest paginationRequest);

}
