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
import com.example.mautic_mobile.activities.campaign_detail.CampaignDetailActivity;
import com.example.mautic_mobile.activities.campaign_detail.fragments.DetailCampaignFragment;
import com.example.mautic_mobile.activities.home.presenter.interfaces.CampaignActionInterface;
import com.example.mautic_mobile.activities.home.presenter.interfaces.CampaignsFragmentContract;
import com.example.mautic_mobile.activities.home.presenter.CampaignsFragmentPresenterImpl;
import com.example.mautic_mobile.adapters.CampaignAdapter;
import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.Utils;

import java.util.List;


public class CampaignsFragment extends Fragment implements CampaignsFragmentContract.CampaignsFragmentView, CampaignActionInterface {
    private static CampaignsFragment INSTANCE = null;
    private ProgressBar progressBar;
    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayout contentLayout;
    private LinearLayout messageLayout;
    private TextView messageTextView;

    private CampaignsFragment() {
    }

    public static CampaignsFragment newInstance() {
        if (INSTANCE == null) {
            return new CampaignsFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_campaigns, container, false);
        INSTANCE = this;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recycler_view_campaign);
        contentLayout = rootView.findViewById(R.id.content_fragment_campaigns);
        messageLayout = rootView.findViewById(R.id.no_element_linear_layout);
        messageTextView = rootView.findViewById(R.id.message_no_elements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar = rootView.findViewById(R.id.progress_bar_campaign);
        String userName = Utils.getUserNameFromPreferences(getContext());
        String passWord = Utils.getUserPswFromPreferences(getContext());
        String endPoint = Utils.getUserEndPointFromPreferences(getContext());
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        CampaignsFragmentPresenterImpl mainPresenter = new CampaignsFragmentPresenterImpl(this, mauticClient);
        mainPresenter.onActivityCreated();
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
        messageTextView.setText(R.string.msg_no_campaign);
    }

    @Override
    public void hideMessageLayout() {
        messageLayout.setVisibility(View.GONE);
    }

    @Override
    public void setDataToAdapter(List<Campaign> campaignList) {
        CampaignAdapter campaignAdapter = new CampaignAdapter(this);
        recyclerView.setAdapter(campaignAdapter);
        for (int i = 0; i < campaignList.size(); i++) {
            campaignAdapter.bindViewModel(campaignList.get(i));
        }
    }

    @Override
    public void onCampaignClick(Campaign campaign) {
        Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
        intent.putExtra(CampaignDetailActivity.ID_CAMPAIGN_DETAIL_ACTIVITY, campaign.getId());
        startActivity(intent);
    }
}
