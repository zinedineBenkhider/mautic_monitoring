package com.example.mautic_mobile.activities.form_detail;

import com.example.mautic_mobile.api.entities.FormSubmission;

import java.util.List;

public interface FormDetailContract {
    interface FormDetailView {
        void showProgress();

        void hideProgress();

        void setDataToAdapter(List<FormSubmission> contactList);

        void showContentLayout();

        void hideContentLayout();

        void showMessageLayout();

        void hideMessageLayout();
    }

    interface FormDetailPresenter {
        void onActivityLoaded(int formId);
    }
}
