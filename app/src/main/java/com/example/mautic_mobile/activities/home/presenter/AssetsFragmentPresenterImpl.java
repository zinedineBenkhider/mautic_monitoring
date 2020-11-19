package com.example.mautic_mobile.activities.home.presenter;

import com.example.mautic_mobile.activities.home.presenter.interfaces.AssetFragmentContract;
import com.example.mautic_mobile.api.entities.Asset;
import com.example.mautic_mobile.api.entities.AssetsResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AssetsFragmentPresenterImpl implements AssetFragmentContract.AssetsFragmentPresenter {
    private AssetFragmentContract.AssetsFragmentView view;
    private MauticClient mauticClient;
    public AssetsFragmentPresenterImpl(AssetFragmentContract.AssetsFragmentView view, MauticClient mauticClient){
        this.view=view;
        this.mauticClient=mauticClient;
    }
    @Override
    public void onActivityCreated() {
        view.showProgress();
        mauticClient.getAllAsssets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AssetsResponse>() {
                    private List<Asset> assets=new ArrayList<>();
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                        if (assets.size()==0){
                            view.hideContentLayout();
                            view.showMessageLayout();
                        }
                        else{
                            view.showContentLayout();
                            view.hideMessageLayout();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(AssetsResponse assetsResponse) {
                        assets=assetsResponse.getAssets();
                        view.setDataToAdapter(assets);

                    }
                });
    }
}
