package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private String applicantEmail;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    public Application() {
    }

    public Application(Long id, String applicantName, String applicantEmail, ApplicationStatus status, Job job) {
        this.id = id;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.status = status;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Job getJob() {
        return job;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
