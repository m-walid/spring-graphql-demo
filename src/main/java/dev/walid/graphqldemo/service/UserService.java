package dev.walid.graphqldemo.service;

import dev.walid.graphqldemo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
}
