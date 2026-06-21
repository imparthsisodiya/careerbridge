package com.careerbridge.controller;

import com.careerbridge.dto.ApiResponse;
import com.careerbridge.dto.SkillRequest;
import com.careerbridge.entity.Skill;
import com.careerbridge.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin("*")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public ApiResponse<Skill> addSkill(@RequestBody SkillRequest request) {
        return new ApiResponse<>(true, "Skill added successfully", skillService.addSkill(request));
    }

    @GetMapping
    public ApiResponse<List<Skill>> getSkills(@RequestParam String studentEmail) {
        return new ApiResponse<>(true, "Skills fetched", skillService.getSkillsByStudent(studentEmail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return new ApiResponse<>(true, "Skill deleted", "Deleted");
    }
}
