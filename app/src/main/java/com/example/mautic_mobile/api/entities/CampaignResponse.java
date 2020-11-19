package com.example.mautic_mobile.api.entities;

import java.util.Map;

public class CampaignResponse {
    private int total;
    private Map<Integer, Campaign> campaigns;
    private Campaign campaign;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<Integer, Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Map<Integer, Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public CampaignResponse(int total, Map<Integer, Campaign> campaigns, Campaign campaign) {
        this.total = total;
        this.campaigns = campaigns;
        this.campaign = campaign;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
