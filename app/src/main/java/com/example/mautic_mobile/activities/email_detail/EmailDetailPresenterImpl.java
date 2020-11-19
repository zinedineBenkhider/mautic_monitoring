package com.example.mautic_mobile.activities.email_detail;

import com.example.mautic_mobile.api.entities.EmailResponse;
import com.example.mautic_mobile.api.entities.EmailsResponse;
import com.example.mautic_mobile.api.entities.FormSubmissionsResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EmailDetailPresenterImpl implements EmailDetailContract.EmailDetailPresenter {
    private MauticClient mauticClient;
    private EmailDetailContract.EmailDetailView detailView;

    public EmailDetailPresenterImpl(MauticClient mauticClient, EmailDetailContract.EmailDetailView detailView) {
        this.mauticClient = mauticClient;
        this.detailView = detailView;
    }

    @Override
    public void onActivityLoaded(int emailId) {
        detailView.showProgress();
        detailView.hideContentLayout();
        mauticClient.getEmailById(emailId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error getEmailById: " + e.getMessage());
                    }

                    @Override
                    public void onNext(EmailResponse emailResponse) {
                        if (emailResponse.getEmail().getUnsubscribeForm() != null) {
                            mauticClient.getFormSubmissionById(emailResponse.getEmail().getUnsubscribeForm().getId())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<FormSubmissionsResponse>() {
                                        @Override
                                        public void onCompleted() {
                                            detailView.hideProgress();
                                            detailView.showContentLayout();
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            System.out.println(e.getMessage());
                                        }

                                        @Override
                                        public void onNext(FormSubmissionsResponse formSubmissionsResponse) {
                                            detailView.setDataToAdapter(formSubmissionsResponse.getTotal(), emailResponse.getEmail());
                                            detailView.hideProgress();
                                            detailView.showContentLayout();
                                        }
                                    });
                        } else {
                            detailView.setDataToAdapter(0, emailResponse.getEmail());
                            detailView.hideProgress();
                            detailView.showContentLayout();
                        }

                    }
                });
    }
}
