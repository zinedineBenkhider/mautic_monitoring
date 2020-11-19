package com.example.mautic_mobile.activities.login;

public interface LogInContract {
    interface LoginView {
        void showProgress();

        void hideProgress();

        void disableConnectionBtn();

        void activateConnectionBtn();

        void resultConnection(Boolean success,String msg);
    }

    interface LoginPresenter {
        void login();
    }
}
