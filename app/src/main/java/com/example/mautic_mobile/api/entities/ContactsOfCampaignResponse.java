package com.example.mautic_mobile.api.entities;

import java.util.List;

public class ContactsOfCampaignResponse {

    private int total;
    private List<ContactsOfCampaign> contacts;

    public ContactsOfCampaignResponse(int total, List<ContactsOfCampaign> contacts) {
        this.total = total;
        this.contacts = contacts;
    }

    public List<ContactsOfCampaign> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsOfCampaign> contacts) {
        this.contacts = contacts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
