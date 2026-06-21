package com.careerbridge.dto;

public class JobFilterRequest {
    private String keyword;
    private String location;
    private String type;
    private String experienceRequired;

    public String getKeyword() {
        return keyword;
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

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
}
