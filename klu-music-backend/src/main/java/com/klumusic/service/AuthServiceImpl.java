package com.klumusic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klumusic.dto.RegisterRequest;
import com.klumusic.entity.User;
import com.klumusic.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }
}
