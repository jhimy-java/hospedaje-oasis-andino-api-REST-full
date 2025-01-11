package com.oasisandino.backend.domain.repository;

import com.oasisandino.backend.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUsers();
    Optional<User> getUserById(int id);
    User saveUser(User user);
    void deleteUserById(int id);
}
