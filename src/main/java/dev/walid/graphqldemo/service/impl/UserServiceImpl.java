package dev.walid.graphqldemo.service.impl;

import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.repository.UserRepository;
import dev.walid.graphqldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
