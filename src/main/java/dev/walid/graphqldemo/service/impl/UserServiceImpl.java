package dev.walid.graphqldemo.service.impl;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.repository.UserRepository;
import dev.walid.graphqldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public PaginatedResponse<List<User>> getAllUsers(PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), Sort.Direction.DESC, "id");
        Page<User> usersPage = userRepository.findAll(pageRequest);
        return new PaginatedResponse<>(usersPage.getContent(), usersPage.getTotalElements(), usersPage.getTotalPages());
    }

    @Override
    public PaginatedResponse<List<User>> getUserFollowers(User user, PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), Sort.Direction.DESC, "id");
        Page<User> usersPage = userRepository.findAllByFollowers(user, pageRequest);
        return new PaginatedResponse<>(usersPage.getContent(), usersPage.getTotalElements(), usersPage.getTotalPages());
    }

    @Override
    public PaginatedResponse<List<User>> getUserFollowing(User user, PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), Sort.Direction.DESC, "id");
        Page<User> usersPage = userRepository.findAllByFollowing(user, pageRequest);
        return new PaginatedResponse<>(usersPage.getContent(), usersPage.getTotalElements(), usersPage.getTotalPages());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

}
