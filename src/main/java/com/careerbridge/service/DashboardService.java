package com.careerbridge.service;

import com.careerbridge.dto.DashboardStatsResponse;
import com.careerbridge.entity.Application;
import com.careerbridge.entity.SavedJob;
import com.careerbridge.repository.ApplicationRepository;
import com.careerbridge.repository.NotificationRepository;
import com.careerbridge.repository.SavedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public DashboardStatsResponse getStudentStats(String email) {
        List<Application> applications = applicationRepository.findByApplicantEmail(email);
        List<SavedJob> savedJobs = savedJobRepository.findByUserEmail(email);
        long notificationCount = notificationRepository.findByUserEmail(email).size();

        long interviewCount = applications.stream()
                .filter(a -> a.getStatus().name().equals("SHORTLISTED"))
                .count();

        return new DashboardStatsResponse(
                applications.size(),
                savedJobs.size(),
                interviewCount,
                notificationCount
        );
    }
}