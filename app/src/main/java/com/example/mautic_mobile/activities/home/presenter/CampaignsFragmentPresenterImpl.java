package com.example.mautic_mobile.activities.home.presenter;

import com.example.mautic_mobile.activities.home.presenter.interfaces.CampaignsFragmentContract;
import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.api.entities.CampaignResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CampaignsFragmentPresenterImpl implements CampaignsFragmentContract.CampaignsFragmentPresenter {
   private CampaignsFragmentContract.CampaignsFragmentView mainView;
   private MauticClient mauticClient;
    public CampaignsFragmentPresenterImpl(CampaignsFragmentContract.CampaignsFragmentView mainView, MauticClient mauticClient){
        this.mainView=mainView;
        this.mauticClient=mauticClient;
    }
    @Override
    public void onActivityCreated() {
        mainView.showProgress();
        mauticClient.getAllCampaigns()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CampaignResponse>() {
                    private List<Campaign> campaigns=new ArrayList<>();
                    @Override
                    public void onCompleted() {
                        mainView.hideProgress();
                        if (campaigns.isEmpty()){
                            mainView.hideContentLayout();
                            mainView.showMessageLayout();
                        }
                        else{
                            mainView.showContentLayout();
                            mainView.hideMessageLayout();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.hideProgress();
                    }

                    @Override
                    public void onNext(CampaignResponse campaignResponse) {
                        campaigns=new ArrayList<>(campaignResponse.getCampaigns().values());
                        mainView.setDataToAdapter(campaigns);
                    }
                });
    }
}
