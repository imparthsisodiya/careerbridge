package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String techStack;
    private String githubLink;
    private String liveLink;
    private String studentEmail;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTechStack() {
        return techStack;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public String getLiveLink() {
        return liveLink;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}