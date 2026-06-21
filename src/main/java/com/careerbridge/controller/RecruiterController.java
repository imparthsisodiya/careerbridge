package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.UserResponse;
import com.careerbridge.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiters")
@CrossOrigin("*")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/{email}")
    public ApiResponse<UserResponse> getRecruiterByEmail(@PathVariable String email) {
        return new ApiResponse<>(true, "Recruiter fetched",
                recruiterService.getRecruiterByEmail(email));
    }
}