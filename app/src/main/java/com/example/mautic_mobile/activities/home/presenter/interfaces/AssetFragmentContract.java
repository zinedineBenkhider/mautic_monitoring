package com.example.mautic_mobile.activities.home.presenter.interfaces;
import com.example.mautic_mobile.api.entities.Asset;
import java.util.List;
public interface AssetFragmentContract {
    interface AssetsFragmentView {
        void showProgress();
        void hideProgress();
        void setDataToAdapter(List<Asset> assets);
        void showContentLayout();
        void hideContentLayout();
        void showMessageLayout();
        void hideMessageLayout();
    }

    interface AssetsFragmentPresenter {
        void onActivityCreated();
    }
}
