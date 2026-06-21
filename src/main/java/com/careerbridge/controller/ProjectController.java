package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.ProjectRequest;
import com.careerbridge.entity.Project;
import com.careerbridge.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ApiResponse<Project> addProject(@RequestBody ProjectRequest request) {
        return new ApiResponse<>(true, "Project added successfully",
                projectService.addProject(request));
    }

    @GetMapping
    public ApiResponse<List<Project>> getProjects(@RequestParam String studentEmail) {
        return new ApiResponse<>(true, "Projects fetched",
                projectService.getProjectsByStudent(studentEmail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ApiResponse<>(true, "Project deleted", "Deleted");
    }
}
