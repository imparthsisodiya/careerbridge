package com.careerbridge.service;

import com.careerbridge.dto.UserResponse;
import com.careerbridge.entity.User;
import com.careerbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getRecruiterByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getPhone(),
                user.getLocation(),
                user.getSkills(),
                user.getBio(),
                user.getProfileImage(),
                user.getResumeFileName()
        );
    }
}
