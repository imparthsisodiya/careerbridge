package com.careerbridge.service;

import com.careerbridge.config.JwtService;
import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.AuthResponse;
import com.careerbridge.entity.Role;
import com.careerbridge.entity.User;
import com.careerbridge.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class GoogleAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Value("${google.client.id}")
    private String googleClientId;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponse<AuthResponse> loginWithGoogle(String idToken, String requestedRole) {
        try {
            // Verify token using Google's tokeninfo endpoint
            String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            String responseBody = restTemplate.getForObject(url, String.class);
            JsonNode node = objectMapper.readTree(responseBody);

            String audience = node.path("aud").asText();
            if (!googleClientId.equals(audience)) {
                return new ApiResponse<>(false, "Invalid Google token.", null);
            }

            String email = node.path("email").asText();
            String name = node.path("name").asText();
            boolean emailVerified = node.path("email_verified").asBoolean(false);

            if (email == null || email.isEmpty() || !emailVerified) {
                return new ApiResponse<>(false, "Google email not verified.", null);
            }

            User user = userRepository.findByEmail(email).orElse(null);

            if (user == null) {
                user = new User();
                user.setName(name != null && !name.isEmpty() ? name : email.split("@")[0]);
                user.setEmail(email);
                // Random unusable password since login is via Google
                user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                Role role = "RECRUITER".equalsIgnoreCase(requestedRole) ? Role.RECRUITER : Role.STUDENT;
                user.setRole(role);
                user = userRepository.save(user);
            }

            String token = jwtService.generateToken(user.getEmail());

            AuthResponse response = new AuthResponse();
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole().name());
            response.setToken(token);

            return new ApiResponse<>(true, "Google login successful.", response);

        } catch (Exception e) {
            return new ApiResponse<>(false, "Google login failed: " + e.getMessage(), null);
        }
    }
}
