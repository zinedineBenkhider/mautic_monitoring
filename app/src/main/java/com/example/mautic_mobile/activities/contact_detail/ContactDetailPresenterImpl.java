package com.example.mautic_mobile.activities.contact_detail;

import com.example.mautic_mobile.api.entities.ContactResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ContactDetailPresenterImpl implements ContactDetailContract.ContactDetailPresenter {

    private MauticClient mauticClient;
    private ContactDetailContract.ContactDetailView contactDetailView;

    public ContactDetailPresenterImpl(ContactDetailContract.ContactDetailView contactDetailView, MauticClient mauticClient) {
        this.mauticClient = mauticClient;
        this.contactDetailView = contactDetailView;
    }

    @Override
    public void onActivityLoaded(int idContact) {
        contactDetailView.hideContent();
        contactDetailView.showProgress();
        mauticClient.getContactById(idContact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContactResponse>() {
                    @Override
                    public void onNext(ContactResponse contactResponse) {
                        contactDetailView.setDataToFields(contactResponse.getContactDetail());
                        System.out.println(contactResponse.getContactDetail().getFirstName());
                    }

                    @Override
                    public void onCompleted() {
                        contactDetailView.showContent();
                        contactDetailView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
