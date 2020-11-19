package com.example.mautic_mobile.activities.home.presenter.interfaces;

import com.example.mautic_mobile.api.entities.Campaign;

import java.util.List;

public interface CampaignsFragmentContract {
    interface CampaignsFragmentView {
        void showProgress();
        void hideProgress();
        void showContentLayout();
        void hideContentLayout();
        void showMessageLayout();
        void hideMessageLayout();
        void setDataToAdapter(List<Campaign> campaignList);
    }

    interface CampaignsFragmentPresenter {
        void onActivityCreated();
    }
}
