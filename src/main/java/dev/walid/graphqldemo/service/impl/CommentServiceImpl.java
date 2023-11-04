package dev.walid.graphqldemo.service.impl;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.model.Comment;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.repository.CommentRepository;
import dev.walid.graphqldemo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public PaginatedResponse<List<Comment>> getPostComments(Post post, PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize());
        Page<Comment> commentsPage = commentRepository.findAllByPost(post, pageRequest);
        return new PaginatedResponse<>(commentsPage.getContent(), commentsPage.getTotalElements(), commentsPage.getTotalPages());
    }
}
