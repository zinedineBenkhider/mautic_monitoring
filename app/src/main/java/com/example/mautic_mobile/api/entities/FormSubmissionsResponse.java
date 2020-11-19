package com.example.mautic_mobile.api.entities;

import java.util.List;

public class FormSubmissionsResponse {
    private int total;
    private List<FormSubmission> submissions;

    public FormSubmissionsResponse(int total, List<FormSubmission> submissions) {
        this.total = total;
        this.submissions = submissions;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FormSubmission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }
}
