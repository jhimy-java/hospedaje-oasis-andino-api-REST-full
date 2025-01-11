package com.oasisandino.backend.domain.service;

import com.oasisandino.backend.domain.User;
import com.oasisandino.backend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAllUsers()
    {
        return userRepository.findAllUsers();
    }
    public Optional<User> getUserById(int id)
    {
        return userRepository.getUserById(id);
    }
    public User saveUser(User user)
    {
        return userRepository.saveUser(user);
    }
    public boolean deleteUserById(int id)
    {
        return getUserById(id).map(user -> {
            userRepository.deleteUserById(id);
            return true;
        }).orElse(false);
    }

}
