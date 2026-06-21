package com.careerbridge.dto;

public class DashboardStatsResponse {

    private long appliedCount;
    private long savedCount;
    private long interviewCount;
    private long notificationCount;

    public DashboardStatsResponse() {
    }

    public DashboardStatsResponse(long appliedCount, long savedCount, long interviewCount, long notificationCount) {
        this.appliedCount = appliedCount;
        this.savedCount = savedCount;
        this.interviewCount = interviewCount;
        this.notificationCount = notificationCount;
    }

    public long getAppliedCount() {
        return appliedCount;
    }

    public long getSavedCount() {
        return savedCount;
    }

    public long getInterviewCount() {
        return interviewCount;
    }

    public long getNotificationCount() {
        return notificationCount;
    }

    public void setAppliedCount(long appliedCount) {
        this.appliedCount = appliedCount;
    }

    public void setSavedCount(long savedCount) {
        this.savedCount = savedCount;
    }

    public void setInterviewCount(long interviewCount) {
        this.interviewCount = interviewCount;
    }

    public void setNotificationCount(long notificationCount) {
        this.notificationCount = notificationCount;
    }
}