package com.example.TheLinkTamer.service;

import com.example.TheLinkTamer.model1.User;
import com.example.TheLinkTamer.repository1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(String uuid) {
        Optional<User> userOpt = userRepository.findByUuid(uuid);
        return userOpt.orElseGet(() -> {
            User newUser = new User();
            newUser.setUuid(uuid);
            return userRepository.save(newUser);
        });
    }
}

