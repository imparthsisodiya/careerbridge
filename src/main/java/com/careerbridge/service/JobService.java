package com.careerbridge.service;

import com.careerbridge.dto.JobRequest;
import com.careerbridge.entity.Job;
import com.careerbridge.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job addJob(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setType(request.getType());
        job.setExperienceRequired(request.getExperienceRequired());
        job.setSalaryRange(request.getSalaryRange());
        job.setDescription(request.getDescription());
        job.setRecruiterEmail(request.getRecruiterEmail());

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
}
