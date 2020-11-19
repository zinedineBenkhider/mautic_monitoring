package com.example.mautic_mobile.activities.home.presenter.interfaces;
import com.example.mautic_mobile.api.entities.Form;

import java.util.List;

public interface FormsFragmentContract {
    interface FormsFragmentView {
        void showProgress();
        void hideProgress();
        void setDataToAdapter(List<Form> forms);
        void showContentLayout();
        void hideContentLayout();
        void showMessageLayout();
        void hideMessageLayout();
    }

    interface FormsFragmentPresenter {
        void onActivityCreated();
    }
}
