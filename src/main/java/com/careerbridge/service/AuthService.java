package com.careerbridge.service;

import com.careerbridge.config.JwtService;
import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.AuthResponse;
import com.careerbridge.dto.LoginRequest;
import com.careerbridge.dto.RegisterRequest;
import com.careerbridge.entity.Role;
import com.careerbridge.entity.User;
import com.careerbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public ApiResponse<AuthResponse> register(RegisterRequest request) {
        String email = request.getEmail().trim();

        if (userRepository.existsByEmail(email)) {
            return new ApiResponse<>(false, "Email already registered.", null);
        }

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        user.setRole(Role.valueOf(request.getRole().trim().toUpperCase()));

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser.getEmail());

        AuthResponse response = new AuthResponse();
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole().name());
        response.setToken(token);

        return new ApiResponse<>(true, "Registration successful.", response);
    }

    public ApiResponse<AuthResponse> login(LoginRequest request) {
        String email = request.getEmail().trim();
        String rawPassword = request.getPassword().trim();

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return new ApiResponse<>(false, "User not found.", null);
        }

        User user = optionalUser.get();
        String storedPassword = user.getPassword();

        boolean passwordMatched = false;

        if (storedPassword != null) {
            if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
                passwordMatched = passwordEncoder.matches(rawPassword, storedPassword);
            } else if (storedPassword.equals(rawPassword)) {
                // Auto-upgrade plain text password to BCrypt
                passwordMatched = true;
                user.setPassword(passwordEncoder.encode(rawPassword));
                userRepository.save(user);
            }
        }

        if (!passwordMatched) {
            return new ApiResponse<>(false, "Invalid password.", null);
        }

        String token = jwtService.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setToken(token);

        return new ApiResponse<>(true, "Login successful.", response);
    }
}
