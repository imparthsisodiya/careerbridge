package com.careerbridge.service;

import com.careerbridge.dto.ApplicationRequest;
import com.careerbridge.dto.ApplicationResponse;
import com.careerbridge.dto.ApiResponse;
import com.careerbridge.entity.Application;
import com.careerbridge.entity.ApplicationStatus;
import com.careerbridge.entity.Job;
import com.careerbridge.repository.ApplicationRepository;
import com.careerbridge.repository.JobRepository;

import com.careerbridge.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmailService emailService;

    public ApiResponse<String> applyToJob(ApplicationRequest request) {
        Job job = jobRepository.findById(request.getJobId()).orElse(null);

        if (job == null) {
            return new ApiResponse<>(false, "Job not found.", null);
        }

        boolean alreadyApplied = applicationRepository
                .existsByApplicantEmailAndJobId(request.getApplicantEmail(), request.getJobId());

        if (alreadyApplied) {
            return new ApiResponse<>(false, "You already applied for this job.", null);
        }

        Application application = new Application();
        application.setApplicantName(request.getApplicantName());
        application.setApplicantEmail(request.getApplicantEmail());
        application.setStatus(ApplicationStatus.PENDING);
        application.setJob(job);

        applicationRepository.save(application);

        // Send confirmation email (non-blocking — won't fail the apply if email fails)
        try {
            emailService.sendApplicationConfirmation(
                request.getApplicantEmail(),
                request.getApplicantName(),
                job.getTitle(),
                job.getCompany()
            );
        } catch (Exception e) {
            System.err.println("Email failed: " + e.getMessage());
        }

        return new ApiResponse<>(true, "Application submitted successfully! Check your email for confirmation.", null);
    }

    public List<ApplicationResponse> getApplicationsByUser(String email) {
        List<Application> applications = applicationRepository.findByApplicantEmail(email);
        List<ApplicationResponse> responseList = new ArrayList<>();

        for (Application application : applications) {
            Job job = application.getJob();
            if (job == null) continue;
            responseList.add(new ApplicationResponse(
                    application.getId(),
                    job.getTitle(),
                    job.getCompany(),
                    job.getLocation(),
                    application.getApplicantName(),
                    application.getApplicantEmail(),
                    application.getStatus() != null ? application.getStatus().name() : "PENDING"
            ));
        }

        return responseList;
    }

    public List<ApplicationResponse> getApplicationsForRecruiter(String recruiterEmail) {
        List<Application> applications = applicationRepository.findByJobRecruiterEmail(recruiterEmail);
        List<ApplicationResponse> responseList = new ArrayList<>();

        for (Application application : applications) {
            Job job = application.getJob();
            if (job == null) continue;
            responseList.add(new ApplicationResponse(
                    application.getId(),
                    job.getTitle(),
                    job.getCompany(),
                    job.getLocation(),
                    application.getApplicantName(),
                    application.getApplicantEmail(),
                    application.getStatus() != null ? application.getStatus().name() : "PENDING"
            ));
        }

        return responseList;
    }

    public ApiResponse<String> updateApplicationStatus(Long id, String status) {
        Application application = applicationRepository.findById(id).orElse(null);

        if (application == null) {
            return new ApiResponse<>(false, "Application not found.", null);
        }

        try {
            application.setStatus(ApplicationStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(false, "Invalid status value: " + status, null);
        }

        applicationRepository.save(application);
        return new ApiResponse<>(true, "Application status updated.", null);
    }
}
