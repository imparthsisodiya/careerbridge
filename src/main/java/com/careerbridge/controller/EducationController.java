package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.EducationRequest;
import com.careerbridge.entity.Education;
import com.careerbridge.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@CrossOrigin("*")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping
    public ApiResponse<Education> addEducation(@RequestBody EducationRequest request) {
        return new ApiResponse<>(true, "Education added successfully",
                educationService.addEducation(request));
    }

    @GetMapping
    public ApiResponse<List<Education>> getEducation(@RequestParam String studentEmail) {
        return new ApiResponse<>(true, "Education fetched",
                educationService.getEducationByStudent(studentEmail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return new ApiResponse<>(true, "Education deleted", "Deleted");
    }
}
