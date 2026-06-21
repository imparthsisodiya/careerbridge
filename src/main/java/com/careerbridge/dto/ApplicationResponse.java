package com.careerbridge.dto;

public class ApplicationResponse {

    private Long id;
    private String title;
    private String company;
    private String location;
    private String applicantName;
    private String applicantEmail;
    private String status;

    public ApplicationResponse() {
    }

    public ApplicationResponse(Long id, String title, String company, String location,
                               String applicantName, String applicantEmail, String status) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
