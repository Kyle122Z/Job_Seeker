package com.jobs.jobseeker.model;

public class Jobs {

    private String category, company_name, role, location, job_description;

    public Jobs(String category, String company_name, String role, String location, String job_description){
        this.category = category;
        this.company_name = company_name;
        this.role = role;
        this.location = location;
        this.job_description = job_description;
    }

    public Jobs() {
    }

    public String getCategory() {
        return category;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getRole() {
        return role;
    }

    public String getLocation() {
        return location;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

}

// 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

