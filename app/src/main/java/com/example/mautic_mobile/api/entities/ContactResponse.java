package com.example.mautic_mobile.api.entities;

public class ContactResponse {
    private Contact contact;

    public ContactResponse(Contact contact) {
        this.contact = contact;
    }

    public Contact getContactDetail() {
        return contact;
    }

    public void setContactDetail(Contact contact) {
        this.contact = contact;
    }
}
