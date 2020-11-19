package com.example.mautic_mobile.api.entities;

public class EmailResponse {
    private Email email;

    public EmailResponse(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
