package com.example.mautic_mobile.activities.campaign_detail.presenter;

import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.api.entities.CampaignResponse;
import com.example.mautic_mobile.api.entities.Email;
import com.example.mautic_mobile.api.entities.EmailsResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailPresenterImpl implements DetailContract.ContactPresenter {

    private DetailContract.DetailView detailView;
    private MauticClient mauticClient;

    public DetailPresenterImpl(DetailContract.DetailView detailView, MauticClient mauticClient) {
        this.detailView = detailView;
        this.mauticClient = mauticClient;
    }

    private void onFinish(List<Email> emailList) {
        detailView.hideProgress();
        if (emailList.isEmpty()) {
            detailView.showMessage();
        } else {
            detailView.hideMessage();
        }
        detailView.showContent();
    }

    @Override
    public void onActivityLoaded(int idCampaign) {
        detailView.hideContent();
        detailView.showProgress();
        mauticClient.getCampaignById(idCampaign)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CampaignResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CampaignResponse campaignResponse) {
                        mauticClient.getAllEmails()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<EmailsResponse>() {
                                    private List<Email> emailList = new ArrayList<>();

                                    @Override
                                    public void onCompleted() {
                                        onFinish(emailList);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        onFinish(emailList);
                                    }

                                    @Override
                                    public void onNext(EmailsResponse emailResponse) {
                                        List<Email> allEmails = new ArrayList<>(emailResponse.getEmail().values());
                                        Campaign campaign = campaignResponse.getCampaign();
                                        emailList = new ArrayList<>();
                                        for (int i = 0; i < campaign.getEvents().size(); i++) {

                                            if (campaign.getEvents().get(i).getType().equals("email.send")) {
                                                for (int j = 0; j < allEmails.size(); j++) {
                                                    if (campaign.getEvents().get(i).getChannelId() == allEmails.get(j).getId()) {
                                                        emailList.add(allEmails.get(i));
                                                    }
                                                }
                                            }
                                        }
                                        detailView.displayData(emailList, campaign);
                                    }
                                });
                    }
                });
    }
}
