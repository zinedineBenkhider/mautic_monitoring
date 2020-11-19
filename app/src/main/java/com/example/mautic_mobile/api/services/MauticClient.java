package com.example.mautic_mobile.api.services;

import com.example.mautic_mobile.api.entities.AssetResponse;
import com.example.mautic_mobile.api.entities.AssetsResponse;
import com.example.mautic_mobile.api.entities.CampaignResponse;
import com.example.mautic_mobile.api.entities.ContactResponse;
import com.example.mautic_mobile.api.entities.ContactsResponse;
import com.example.mautic_mobile.api.entities.ContactsOfCampaignResponse;
import com.example.mautic_mobile.api.entities.EmailResponse;
import com.example.mautic_mobile.api.entities.EmailsResponse;
import com.example.mautic_mobile.api.entities.FormSubmissionsResponse;
import com.example.mautic_mobile.api.entities.FormsResponse;

import rx.Observable;

public class MauticClient {
    private MauticService mauticService;

    public MauticClient(String userName, String password, String endPoint) {
        this.mauticService = InitMauticService.getInstance(userName, password, endPoint).getMauticService();
    }

    /*
        return observable of all campaigns
     */
    public Observable<CampaignResponse> getAllCampaigns() {
        return mauticService.campaigns();
    }

    public Observable<ContactsOfCampaignResponse> getContactsOfCampaign(int idCampaign) {
        return mauticService.contactsOfCampaign(idCampaign);
    }

    public Observable<EmailsResponse> getAllEmails() {
        return this.mauticService.emails();
    }

    public Observable<ContactsResponse> getAllContacts() {
        return mauticService.contacts();
    }

    public Observable<CampaignResponse> getCampaignById(int idCampaign) {
        return mauticService.campaignById(idCampaign);
    }

    public Observable<ContactResponse> getContactById(int idContact) {
        return mauticService.contactById(idContact);
    }

    public Observable<AssetsResponse> getAllAsssets() {
        return mauticService.assets();
    }

    public Observable<FormsResponse> getAllForms() {
        return mauticService.forms();
    }

    public Observable<AssetResponse> getAssetById(int assetId) {
        return mauticService.assetById(assetId);
    }

    public Observable<FormSubmissionsResponse> getFormSubmissionById(int assetId) {
        return mauticService.formSubmissionById(assetId);
    }

    public Observable<EmailResponse> getEmailById(int emailId) {
        return mauticService.emailById(emailId);
    }
}



