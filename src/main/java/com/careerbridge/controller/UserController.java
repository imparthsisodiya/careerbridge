package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.UpdateProfileRequest;
import com.careerbridge.entity.User;
import com.careerbridge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ApiResponse<User> getProfile(@RequestParam String email) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            return new ApiResponse<>(false, "User not found.", null);
        }

        return new ApiResponse<>(true, "Profile fetched successfully.", user);
    }

    @PutMapping("/profile")
    public ApiResponse<User> updateProfile(@RequestParam String email, @RequestBody UpdateProfileRequest request) {
        User updatedUser = userService.updateProfile(email, request);

        if (updatedUser == null) {
            return new ApiResponse<>(false, "User not found.", null);
        }

        return new ApiResponse<>(true, "Profile updated successfully.", updatedUser);
    }
}
