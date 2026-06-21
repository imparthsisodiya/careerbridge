package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String type;
    private String experienceRequired;
    private String salaryRange;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String recruiterEmail;
    private String skills;

    public Job() {
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

    public String getType() {
        return type;
    }

    public String getExperienceRequired() {
        return experienceRequired;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public String getDescription() {
        return description;
    }

    public String getRecruiterEmail() {
        return recruiterEmail;
    }

    public String getSkills() {
        return skills;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setExperienceRequired(String experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecruiterEmail(String recruiterEmail) {
        this.recruiterEmail = recruiterEmail;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
