package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.ExperienceRequest;
import com.careerbridge.entity.Experience;
import com.careerbridge.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@CrossOrigin("*")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @PostMapping
    public ApiResponse<Experience> addExperience(@RequestBody ExperienceRequest request) {
        return new ApiResponse<>(true, "Experience added successfully",
                experienceService.addExperience(request));
    }

    @GetMapping
    public ApiResponse<List<Experience>> getExperience(@RequestParam String studentEmail) {
        return new ApiResponse<>(true, "Experience fetched",
                experienceService.getExperienceByStudent(studentEmail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return new ApiResponse<>(true, "Experience deleted", "Deleted");
    }
}
