package com.example.mautic_mobile.activities.form_detail;

import com.example.mautic_mobile.api.entities.FormSubmission;
import com.example.mautic_mobile.api.entities.FormSubmissionsResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FormDetailPresenterImpl implements FormDetailContract.FormDetailPresenter {
    private MauticClient mauticClient;
    private FormDetailContract.FormDetailView formDetailView;

    public FormDetailPresenterImpl(MauticClient mauticClient, FormDetailContract.FormDetailView formSubmissionsView) {
        this.mauticClient = mauticClient;
        this.formDetailView = formSubmissionsView;
    }

    private void onFinish(List<FormSubmission> submissions) {
        formDetailView.hideProgress();
        if (submissions.isEmpty()) {
            formDetailView.showMessageLayout();
        } else {
            formDetailView.hideMessageLayout();
        }
        formDetailView.showContentLayout();
    }

    @Override
    public void onActivityLoaded(int formId) {
        formDetailView.showProgress();
        formDetailView.hideContentLayout();
        formDetailView.hideMessageLayout();
        mauticClient.getFormSubmissionById(formId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FormSubmissionsResponse>() {
                    private List<FormSubmission> submissions = new ArrayList<>();

                    @Override
                    public void onNext(FormSubmissionsResponse formSubmissionsResponse) {
                        submissions = formSubmissionsResponse.getSubmissions();
                        formDetailView.setDataToAdapter(submissions);
                    }

                    @Override
                    public void onCompleted() {
                        onFinish(submissions);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                        onFinish(submissions);
                    }
                });
    }
}
