package com.applicationtracker.controller;

import com.applicationtracker.dto.AuthRequest;
import com.applicationtracker.entity.User;
import com.applicationtracker.service.UserService;
import com.applicationtracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("REGISTER API HIT");
        userService.register(req.getEmail() , req.getPassword(),req.getUsername());

        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
            String token = jwtUtil.generateToken(req.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        User user = userService.findByEmail(email);
        return ResponseEntity.ok(Map.of("email", user.getEmail(),"username",user.getUsername()));
    }
}
