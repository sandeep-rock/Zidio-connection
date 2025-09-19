package com.klumusic.service;

import com.klumusic.dto.RegisterRequest;
import com.klumusic.entity.User;

public interface AuthService {
    User register(RegisterRequest request);
}
