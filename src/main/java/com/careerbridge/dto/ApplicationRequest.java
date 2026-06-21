package com.careerbridge.dto;

public class ApplicationRequest {

    private Long jobId;
    private String applicantName;
    private String applicantEmail;
    private String resumeBase64;
    private String resumeFileName;
    private String coverLetter;

    public ApplicationRequest() {}

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getApplicantEmail() { return applicantEmail; }
    public void setApplicantEmail(String applicantEmail) { this.applicantEmail = applicantEmail; }

    public String getResumeBase64() { return resumeBase64; }
    public void setResumeBase64(String resumeBase64) { this.resumeBase64 = resumeBase64; }

    public String getResumeFileName() { return resumeFileName; }
    public void setResumeFileName(String resumeFileName) { this.resumeFileName = resumeFileName; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
}
