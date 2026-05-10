package com.applicationtracker.controller;

import com.applicationtracker.dto.AuthRequest;
import com.applicationtracker.service.UserService;
import com.applicationtracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")


public class AuthController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req){
        userService.register(req.getUsername() , req.getPassword());

        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword())
        );
        String token = jwtUtil.generateToken(req.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
