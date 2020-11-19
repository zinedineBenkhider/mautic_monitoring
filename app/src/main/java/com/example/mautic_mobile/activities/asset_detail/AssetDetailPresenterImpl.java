package com.example.mautic_mobile.activities.asset_detail;

import com.example.mautic_mobile.api.entities.AssetResponse;
import com.example.mautic_mobile.api.services.MauticClient;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AssetDetailPresenterImpl implements ContractAssetDetail.AssetDetailPresenter {
    private MauticClient mauticClient;
    private ContractAssetDetail.AssetDetailView assetDetailView;

    public AssetDetailPresenterImpl(ContractAssetDetail.AssetDetailView assetDetailView, MauticClient mauticClient) {
        this.mauticClient = mauticClient;
        this.assetDetailView = assetDetailView;
    }

    @Override
    public void onActivityLoaded(int idAsset) {
        assetDetailView.hideContent();
        assetDetailView.showProgress();
        mauticClient.getAssetById(idAsset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AssetResponse>() {
                    @Override
                    public void onNext(AssetResponse assetResponse) {
                        assetDetailView.setDataToFields(assetResponse.getAsset());
                    }

                    @Override
                    public void onCompleted() {
                        assetDetailView.showContent();
                        assetDetailView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
