package com.example.mautic_mobile.activities.email_detail;

import com.example.mautic_mobile.api.entities.Email;

public interface EmailDetailContract {
    interface EmailDetailView {
        void showProgress();

        void hideProgress();

        void setDataToAdapter(int submissions, Email email);

        void showContentLayout();

        void hideContentLayout();
    }

    interface EmailDetailPresenter {
        void onActivityLoaded(int emailId);
    }
}
