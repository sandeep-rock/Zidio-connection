package com.klumusic.service;

import com.klumusic.entity.User;

public interface UserService {
    User findByEmail(String email);
}
