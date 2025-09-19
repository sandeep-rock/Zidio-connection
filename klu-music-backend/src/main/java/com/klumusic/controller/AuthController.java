package com.klumusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klumusic.dto.AuthRequest;
import com.klumusic.dto.AuthResponse;
import com.klumusic.dto.RegisterRequest;
import com.klumusic.entity.User;
import com.klumusic.service.AuthService;
import com.klumusic.service.UserService;
import com.klumusic.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        String token = jwtUtil.generateToken(user.getEmail());
        AuthResponse resp = new AuthResponse();
        resp.setId(user.getId());
        resp.setName(user.getName());
        resp.setEmail(user.getEmail());
        resp.setToken(token);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        User user = userService.findByEmail(request.getEmail());
        String token = jwtUtil.generateToken(user.getEmail());
        AuthResponse resp = new AuthResponse();
        resp.setId(user.getId());
        resp.setName(user.getName());
        resp.setEmail(user.getEmail());
        resp.setToken(token);
        return ResponseEntity.ok(resp);
    }
}
