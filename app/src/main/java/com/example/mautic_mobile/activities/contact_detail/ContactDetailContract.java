package com.example.mautic_mobile.activities.contact_detail;

import com.example.mautic_mobile.api.entities.Contact;

public interface ContactDetailContract {
    interface ContactDetailView {
        void showProgress();

        void hideProgress();

        void setDataToFields(Contact contact);

        void showContent();

        void hideContent();
    }

    interface ContactDetailPresenter {
        void onActivityLoaded(int idContact);
    }
}
