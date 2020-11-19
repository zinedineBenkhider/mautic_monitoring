package com.example.mautic_mobile.api.entities;
    /*
     this entity is different to Contact entity. she is used to get ids of contacts of campaign
   */
public class ContactsOfCampaign {
    private int campaign_id;
    private int lead_id;

    public ContactsOfCampaign(int campaign_id, int lead_id) {
        this.campaign_id = campaign_id;
        this.lead_id = lead_id;
    }

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public int getLead_id() {
        return lead_id;
    }

    public void setLead_id(int lead_id) {
        this.lead_id = lead_id;
    }
}
