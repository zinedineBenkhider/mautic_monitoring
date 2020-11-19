package com.example.mautic_mobile.activities.home.presenter;

import com.example.mautic_mobile.activities.home.presenter.interfaces.CampaignsFragmentContract;
import com.example.mautic_mobile.activities.home.presenter.interfaces.FormsFragmentContract;
import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.api.entities.CampaignResponse;
import com.example.mautic_mobile.api.entities.Form;
import com.example.mautic_mobile.api.entities.FormsResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FormFragmentPresenterImpl implements FormsFragmentContract.FormsFragmentPresenter {
    private FormsFragmentContract.FormsFragmentView view;
    private MauticClient mauticClient;
    public FormFragmentPresenterImpl(FormsFragmentContract.FormsFragmentView view, MauticClient mauticClient){
        this.view=view;
        this.mauticClient=mauticClient;
    }
    @Override
    public void onActivityCreated() {
        view.showProgress();
        mauticClient.getAllForms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FormsResponse>() {
                    private  List<Form> formList=new ArrayList<>();
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                        if (formList.isEmpty()){
                            view.hideContentLayout();
                            view.showMessageLayout();
                        }
                        else {
                            view.showContentLayout();
                            view.hideMessageLayout();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(FormsResponse formsResponse) {
                        formList=formsResponse.getFormList();
                        view.setDataToAdapter(formList);
                    }
                });
    }
}
