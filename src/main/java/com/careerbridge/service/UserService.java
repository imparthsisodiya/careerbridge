package com.careerbridge.service;

import com.careerbridge.dto.UpdateProfileRequest;
import com.careerbridge.entity.User;
import com.careerbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    public User updateProfile(String currentEmail, UpdateProfileRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(currentEmail);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setLocation(request.getLocation());
        user.setDegree(request.getDegree());
        user.setCollege(request.getCollege());
        user.setEducationYear(request.getEducationYear());
        user.setAbout(request.getAbout());
        user.setBio(request.getAbout());
        user.setLinkedIn(request.getLinkedIn());

        if (request.getProfileImage() != null && !request.getProfileImage().isBlank()) {
            user.setProfileImage(request.getProfileImage());
        }

        if (request.getResumeFileName() != null && !request.getResumeFileName().isBlank()) {
            user.setResumeFileName(request.getResumeFileName());
        }

        if (request.getResumePath() != null && !request.getResumePath().isBlank()) {
            user.setResumePath(request.getResumePath());
        }

        return userRepository.save(user);
    }
}
