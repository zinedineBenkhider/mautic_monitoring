package com.example.mautic_mobile.api.entities;

import java.util.Map;

public class ContactsResponse {
    private int total;
    private Map<String,Contact> contacts;
    public ContactsResponse(int total, Map<String, Contact> contactMap){
        this.total = total;
        this.contacts = contactMap;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }

}
