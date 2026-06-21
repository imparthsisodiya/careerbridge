package com.careerbridge.service;

import com.careerbridge.entity.Job;
import com.careerbridge.entity.User;
import com.careerbridge.repository.ApplicationRepository;
import com.careerbridge.repository.JobRepository;
import com.careerbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Map<String, Long> getAdminStats() {
        Map<String, Long> stats = new HashMap<>();

        List<User> users = userRepository.findAll();

        long totalUsers = users.size();
        long totalRecruiters = users.stream()
                .filter(user -> user.getRole() != null && user.getRole().name().equals("RECRUITER"))
                .count();

        long totalJobs = jobRepository.count();
        long totalApplications = applicationRepository.count();

        stats.put("totalUsers", totalUsers);
        stats.put("totalRecruiters", totalRecruiters);
        stats.put("totalJobs", totalJobs);
        stats.put("totalApplications", totalApplications);

        return stats;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
