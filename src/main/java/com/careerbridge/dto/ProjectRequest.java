package com.careerbridge.dto;

public class ProjectRequest {
    private String title;
    private String techStack;
    private String githubLink;
    private String liveLink;
    private String description;
    private String studentEmail;

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

    public String getDescription() {
        return description;
    }

    public String getStudentEmail() {
        return studentEmail;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
