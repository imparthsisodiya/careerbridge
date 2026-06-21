package com.careerbridge.controller;

import com.careerbridge.dto.ApplicationRequest;
import com.careerbridge.dto.ApplicationResponse;
import com.careerbridge.dto.ApiResponse;
import com.careerbridge.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ApiResponse<String> applyToJob(@RequestBody ApplicationRequest request) {
        return applicationService.applyToJob(request);
    }

    @GetMapping("/user")
    public List<ApplicationResponse> getUserApplications(@RequestParam String email) {
        return applicationService.getApplicationsByUser(email);
    }

    @GetMapping("/recruiter")
    public List<ApplicationResponse> getRecruiterApplications(@RequestParam String email) {
        return applicationService.getApplicationsForRecruiter(email);
    }

    @PutMapping("/{id}/status")
    public ApiResponse<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return applicationService.updateApplicationStatus(id, status);
    }
}
