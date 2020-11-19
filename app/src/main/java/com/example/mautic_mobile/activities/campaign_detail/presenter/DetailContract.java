package com.example.mautic_mobile.activities.campaign_detail.presenter;

import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.api.entities.Contact;
import com.example.mautic_mobile.api.entities.Email;

import java.util.List;

public interface DetailContract {
    interface DetailView {
        void showProgress();

        void hideProgress();

        void showContent();

        void hideContent();

        void showMessage();

        void hideMessage();

        void displayData(List<Email> emailList, Campaign campaign);
    }

    interface ContactPresenter {
        void onActivityLoaded(int idCampaign);
    }
}
