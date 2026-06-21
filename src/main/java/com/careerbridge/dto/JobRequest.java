package com.careerbridge.dto;

public class JobRequest {

    private String title;
    private String company;
    private String location;
    private String type;
    private String experienceRequired;
    private String salaryRange;
    private String description;
    private String recruiterEmail;

    public JobRequest() {
    }

    // ✅ FIXED CONSTRUCTOR
    public JobRequest(String title, String company, String location, String type,
                      String experienceRequired, String salaryRange,
                      String description, String recruiterEmail) {

        this.title = title;
        this.company = company;
        this.location = location;
        this.type = type;
        this.experienceRequired = experienceRequired;
        this.salaryRange = salaryRange;
        this.description = description;
        this.recruiterEmail = recruiterEmail; // 🔥 important
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getExperienceRequired() { return experienceRequired; }
    public void setExperienceRequired(String experienceRequired) { this.experienceRequired = experienceRequired; }

    public String getSalaryRange() { return salaryRange; }
    public void setSalaryRange(String salaryRange) { this.salaryRange = salaryRange; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // 🔥 IMPORTANT
    public String getRecruiterEmail() { return recruiterEmail; }
    public void setRecruiterEmail(String recruiterEmail) { this.recruiterEmail = recruiterEmail; }
}
