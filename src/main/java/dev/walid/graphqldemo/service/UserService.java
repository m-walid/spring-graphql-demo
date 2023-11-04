package dev.walid.graphqldemo.service;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.model.User;

import java.util.List;

public interface UserService {
    PaginatedResponse<List<User>> getAllUsers(PaginationRequest paginationRequest);
    public PaginatedResponse<List<User>> getUserFollowers(User user, PaginationRequest paginationRequest);
    public PaginatedResponse<List<User>> getUserFollowing(User user, PaginationRequest paginationRequest);
    User getUserById(Long id);
}
