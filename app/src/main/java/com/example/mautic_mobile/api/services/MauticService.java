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

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MauticService {

    @GET("/api/campaigns")
    Observable<CampaignResponse> campaigns();

    @GET("/api/emails")
    Observable<EmailsResponse> emails();

    @GET("/api/emails/{ID}")
    Observable<EmailResponse> emailById(@Path("ID") int id);

    @GET("/api/contacts")
    Observable<ContactsResponse> contacts();

    @GET("/api/campaigns/{ID}/contacts")
    Observable<ContactsOfCampaignResponse> contactsOfCampaign(@Path("ID") int id);

    @GET("/api/campaigns/{ID}")
    Observable<CampaignResponse> campaignById(@Path("ID") int id);

    @GET("/api/contacts/{ID}")
    Observable<ContactResponse> contactById(@Path("ID") int id);

    @GET("/api/assets")
    Observable<AssetsResponse> assets();

    @GET("/api/assets/{ID}")
    Observable<AssetResponse> assetById(@Path("ID") int id);

    @GET("/api/forms")
    Observable<FormsResponse> forms();

    @GET("/api/forms/{ID}/submissions")
    Observable<FormSubmissionsResponse> formSubmissionById(@Path("ID") int id);

}
