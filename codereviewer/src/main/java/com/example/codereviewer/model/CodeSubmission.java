package com.example.codereviewer.model;

public class CodeSubmission {

    private int id;
    private String developerName;
    private String language;
    private String title;
    private String code;
    private String reviewStatus;
    private String comments;



    public CodeSubmission(int id, String developerName, String language, String title, String code) {
        this.id = id;
        this.developerName = developerName;
        this.language = language;
        this.title = title;
        this.code = code;
        this.reviewStatus = "Pending";
        this.comments = "";
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getDeveloperName() { return developerName; }
    public String getLanguage() { return language; }
    public String getTitle() { return title; }
    public String getCode() { return code; }
    public String getReviewStatus() { return reviewStatus; }
    public String getComments() { return comments; }

    public void setDeveloperName(String developerName) { this.developerName = developerName; }
    public void setLanguage(String language) { this.language = language; }
    public void setTitle(String title) { this.title = title; }
    public void setCode(String code) { this.code = code; }
    public void setReviewStatus(String reviewStatus) { this.reviewStatus = reviewStatus; }
    public void setComments(String comments) { this.comments = comments; }
}