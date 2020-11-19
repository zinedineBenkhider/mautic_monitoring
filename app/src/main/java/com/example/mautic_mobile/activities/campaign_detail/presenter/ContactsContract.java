package com.example.mautic_mobile.activities.campaign_detail.presenter;

import com.example.mautic_mobile.activities.campaign_detail.fragments.ContactsCompaignFragment;
import com.example.mautic_mobile.api.entities.Contact;

import java.util.List;

public interface ContactsContract {
    interface ContactView {
        void showProgress();

        void hideProgress();

        void showContentLayout();

        void hideContentLayout();

        void showMessageLayout();

        void hideMessageLayout();

        void setDataToAdapter(List<Contact> contactList);
    }

    interface ContactPresenter {
        void onActivityLoaded(int campaignId);
    }
}
