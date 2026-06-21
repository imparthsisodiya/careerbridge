package com.careerbridge.controller;

import com.careerbridge.dto.DashboardStatsResponse;
import com.careerbridge.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/student")
    public DashboardStatsResponse getStudentDashboard(@RequestParam String email) {
        return dashboardService.getStudentStats(email);
    }
}
