package com.careerbridge.controller;

import com.careerbridge.entity.Job;
import com.careerbridge.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-jobs")
@CrossOrigin("*")
public class SavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @PostMapping
    public String saveJob(@RequestParam String email, @RequestParam Long jobId) {
        savedJobService.saveJob(email, jobId);
        return "Job saved successfully";
    }

    @DeleteMapping
    public String removeJob(@RequestParam String email, @RequestParam Long jobId) {
        savedJobService.removeJob(email, jobId);
        return "Saved job removed successfully";
    }

    @GetMapping
    public List<Job> getSavedJobs(@RequestParam String email) {
        return savedJobService.getSavedJobs(email);
    }
}
