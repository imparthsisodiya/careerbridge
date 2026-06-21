package com.careerbridge.service;

import com.careerbridge.entity.Job;
import com.careerbridge.entity.SavedJob;
import com.careerbridge.repository.JobRepository;
import com.careerbridge.repository.SavedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavedJobService {

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Autowired
    private JobRepository jobRepository;

    public void saveJob(String email, Long jobId) {
        if (!savedJobRepository.existsByUserEmailAndJobId(email, jobId)) {
            savedJobRepository.save(new SavedJob(email, jobId));
        }
    }

    public void removeJob(String email, Long jobId) {
        savedJobRepository.deleteByUserEmailAndJobId(email, jobId);
    }

    public List<Job> getSavedJobs(String email) {
        List<SavedJob> savedJobs = savedJobRepository.findByUserEmail(email);
        List<Job> jobs = new ArrayList<>();

        for (SavedJob savedJob : savedJobs) {
            Job job = jobRepository.findById(savedJob.getJobId()).orElse(null);
            if (job != null) {
                jobs.add(job);
            }
        }

        return jobs;
    }
}
