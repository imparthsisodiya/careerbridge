package com.careerbridge.dto;

public class SavedJobRequest {
    private String email;
    private Long jobId;

    public String getEmail() {
        return email;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
