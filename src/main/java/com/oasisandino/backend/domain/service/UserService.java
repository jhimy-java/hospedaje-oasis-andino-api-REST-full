package com.oasisandino.backend.domain.service;

import com.oasisandino.backend.domain.User;
import com.oasisandino.backend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {


    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    List<User> findAllUsers()
    {
        return userRepository.findAllUsers();
    }
    Optional<User> getUserById(int id)
    {
        return userRepository.getUserById(id);
    }
    User saveUser(User user)
    {
        return userRepository.saveUser(user);
    }
    void deleteUserById(int id)
    {
        userRepository.deleteUserById(id);
    }



}
