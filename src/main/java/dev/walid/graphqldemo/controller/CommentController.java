package dev.walid.graphqldemo.controller;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.model.Comment;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @SchemaMapping(typeName="Post")
    public PaginatedResponse<List<Comment>> comments(Post post, @Argument PaginationRequest paginationRequest) {
        return commentService.getPostComments(post, paginationRequest);
    }
}
