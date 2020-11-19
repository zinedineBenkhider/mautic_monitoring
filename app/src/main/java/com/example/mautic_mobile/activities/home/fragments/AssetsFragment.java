package com.example.mautic_mobile.activities.home.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.asset_detail.ActivityAssetDetail;
import com.example.mautic_mobile.activities.home.presenter.interfaces.AssetActionInterface;
import com.example.mautic_mobile.activities.home.presenter.interfaces.AssetFragmentContract;
import com.example.mautic_mobile.activities.home.presenter.AssetsFragmentPresenterImpl;
import com.example.mautic_mobile.adapters.AssetAdapter;
import com.example.mautic_mobile.adapters.ContactsAdapter;
import com.example.mautic_mobile.api.entities.Asset;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.Utils;

import java.util.List;


public class AssetsFragment extends Fragment implements AssetFragmentContract.AssetsFragmentView, AssetActionInterface {
    private static AssetsFragment INSTANCE = null;
    private AssetAdapter assetAdapter;
    private RecyclerView recyclerView;
    private View rootView;
    private ProgressBar progressBar;
    private LinearLayout contentLayout;
    private LinearLayout messageLayout;
    private TextView messageNoContactTextView;

    private AssetsFragment() {
    }

    public static AssetsFragment newInstance() {
        if (INSTANCE == null) {
            return new AssetsFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_assets, container, false);
        INSTANCE = this;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar = rootView.findViewById(R.id.progress_bar_asset);
        contentLayout = rootView.findViewById(R.id.content_asset_fragment);
        messageLayout = rootView.findViewById(R.id.no_element_linear_layout);
        messageNoContactTextView = rootView.findViewById(R.id.message_no_elements);
        assetAdapter = new AssetAdapter(this);
        recyclerView = rootView.findViewById(R.id.recycler_view_asset);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(assetAdapter);

        String userName = Utils.getUserNameFromPreferences(getContext());
        String passWord = Utils.getUserPswFromPreferences(getContext());
        String endPoint = Utils.getUserEndPointFromPreferences(getContext());
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        AssetsFragmentPresenterImpl fragmentPresenter = new AssetsFragmentPresenterImpl(this, mauticClient);
        fragmentPresenter.onActivityCreated();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToAdapter(List<Asset> assets) {
        for (int i = 0; i < assets.size(); i++) {
            assetAdapter.bindViewModel(assets.get(i));
        }
    }

    @Override
    public void showContentLayout() {
        contentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentLayout() {
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessageLayout() {
        messageLayout.setVisibility(View.VISIBLE);
        messageNoContactTextView.setText(R.string.msg_no_asset);
    }

    @Override
    public void hideMessageLayout() {
        messageLayout.setVisibility(View.GONE);
    }


    @Override
    public void onFormClick(Asset asset) {
        Intent intent = new Intent(getActivity(), ActivityAssetDetail.class);
        intent.putExtra(ActivityAssetDetail.ID_ASSET_DETAIL_ASSET, asset.getId());
        startActivity(intent);

    }
}
