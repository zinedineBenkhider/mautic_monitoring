package com.example.mautic_mobile.activities.asset_detail;

import com.example.mautic_mobile.api.entities.Asset;

public interface ContractAssetDetail {
    interface AssetDetailView {
        void showProgress();

        void hideProgress();

        void setDataToFields(Asset asset);

        void showContent();

        void hideContent();
    }

    interface AssetDetailPresenter {
        void onActivityLoaded(int idAsset);
    }
}
