package com.example.mautic_mobile.api.entities;

import java.util.Map;

public class EmailsResponse {
    private Map<Integer,Email> emails;

    public Map<Integer, Email> getEmail() {
        return emails;
    }

    public void setEmail(Map<Integer, Email> email) {
        this.emails = email;
    }
    public EmailsResponse() {

    }
    public EmailsResponse(Map<Integer, Email> email) {
        this.emails = email;
    }
}
