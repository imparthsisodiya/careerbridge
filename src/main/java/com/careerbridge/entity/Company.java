package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String industry;
    private String location;
    private String type;
    private String rating;
    private String jobsCount;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String skills;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getRating() {
        return rating;
    }

    public String getJobsCount() {
        return jobsCount;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setJobsCount(String jobsCount) {
        this.jobsCount = jobsCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
