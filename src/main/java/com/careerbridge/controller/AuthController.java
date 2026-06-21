
package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.AuthResponse;
import com.careerbridge.dto.ForgotPasswordRequest;
import com.careerbridge.dto.GoogleLoginRequest;
import com.careerbridge.dto.LoginRequest;
import com.careerbridge.dto.RegisterRequest;
import com.careerbridge.service.AuthService;
import com.careerbridge.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private GoogleAuthService googleAuthService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/google")
    public ApiResponse<AuthResponse> googleLogin(@RequestBody GoogleLoginRequest request) {
        return googleAuthService.loginWithGoogle(request.getIdToken(), request.getRole());
    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return new ApiResponse<>(true, "Password reset link sent successfully.", null);
    }
}