package com.careerbridge.controller;

import com.careerbridge.dto.JobRequest;
import com.careerbridge.entity.Job;
import com.careerbridge.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    // ✅ POST job (frontend yahi call kar raha hai)
    @PostMapping
    public Job addJob(@RequestBody JobRequest request) {
        return jobService.addJob(request);
    }

    // ✅ GET all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // ✅ GET job by id
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }
}
