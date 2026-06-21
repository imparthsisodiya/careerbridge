package com.careerbridge.controller;

import com.careerbridge.entity.Job;
import com.careerbridge.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/stats")
    public Map<String, Long> getAdminStats() {
        return adminService.getAdminStats();
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return adminService.getAllJobs();
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {
        adminService.deleteJob(id);
        return "Job deleted successfully by admin";
    }
}