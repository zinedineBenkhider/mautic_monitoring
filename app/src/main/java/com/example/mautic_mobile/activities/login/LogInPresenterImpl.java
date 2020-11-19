package com.example.mautic_mobile.activities.login;

import com.example.mautic_mobile.api.entities.ContactsResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LogInPresenterImpl implements LogInContract.LoginPresenter {
    private MauticClient mauticClient;
    private LogInContract.LoginView loginView;

    public LogInPresenterImpl(MauticClient mauticClient, LogInContract.LoginView view) {
        this.mauticClient = mauticClient;
        this.loginView = view;
    }

    @Override
    public void login() {
        loginView.showProgress();
        loginView.disableConnectionBtn();
        mauticClient.getAllContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContactsResponse>() {
                    @Override
                    public void onCompleted() {
                        loginView.resultConnection(true,"");
                        loginView.hideProgress();
                        loginView.activateConnectionBtn();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.resultConnection(false,e.getCause().getMessage());
                        loginView.hideProgress();
                        loginView.activateConnectionBtn();
                    }

                    @Override
                    public void onNext(ContactsResponse contactsResponse) {
                    }
                });
    }


}
