package com.example.mautic_mobile.activities.campaign_detail.fragments;

import android.content.Intent;
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
import com.example.mautic_mobile.activities.campaign_detail.presenter.DetailContract;
import com.example.mautic_mobile.activities.campaign_detail.presenter.DetailPresenterImpl;
import com.example.mautic_mobile.activities.campaign_detail.EmailActionInterface;
import com.example.mautic_mobile.activities.email_detail.EmailDetailActivity;
import com.example.mautic_mobile.adapters.EmailAdapter;
import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.api.entities.Email;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.Utils;

import java.util.List;


public class DetailCampaignFragment extends Fragment implements DetailContract.DetailView, EmailActionInterface {

    private static DetailCampaignFragment INSTANCE = null;
    private TextView nameTextView,dateAddedTextView, isPublishedTextView, publishedUpTextView, emailCountTextView, messageTextView;
    private ProgressBar progressBar;
    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayout contentLayout, messageLayout;
    private EmailAdapter performanceAdapter;

    private DetailCampaignFragment() {
    }

    public static DetailCampaignFragment newInstance() {
        if (INSTANCE == null) {
            return new DetailCampaignFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_campaign_detail, container, false);
        INSTANCE = this;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar = rootView.findViewById(R.id.campaign_detail_progress_bar);
        nameTextView = rootView.findViewById(R.id.name_campaign_detail_campaign);
        dateAddedTextView=rootView.findViewById(R.id.date_added_detail_campaign);
        isPublishedTextView = rootView.findViewById(R.id.is_published_detail_campaign);
        publishedUpTextView = rootView.findViewById(R.id.published_up_detail_campaign);
        emailCountTextView = rootView.findViewById(R.id.email_count_detail_campaign);
        contentLayout = rootView.findViewById(R.id.content_detail_campaign);
        messageLayout = rootView.findViewById(R.id.content_no_emails);
        messageTextView = rootView.findViewById(R.id.message_no_elements);
        initRecyclerView();

        String userName = Utils.getUserNameFromPreferences(getContext());
        String passWord = Utils.getUserPswFromPreferences(getContext());
        String endPoint = Utils.getUserEndPointFromPreferences(getContext());
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        DetailPresenterImpl detailPresenter = new DetailPresenterImpl(this, mauticClient);
        Intent intent = getActivity().getIntent();

        int campaignId = intent.getIntExtra(CampaignDetailActivity.ID_CAMPAIGN_DETAIL_ACTIVITY, -1);
        detailPresenter.onActivityLoaded(campaignId);
    }

    private void initRecyclerView() {
        recyclerView = getActivity().findViewById(R.id.recycler_view_detail_campaign);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        performanceAdapter = new EmailAdapter(this);
        recyclerView.setAdapter(performanceAdapter);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        contentLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void showMessage() {
        messageLayout.setVisibility(View.VISIBLE);
        messageTextView.setText(R.string.msg_no_email);
    }

    @Override
    public void hideMessage() {
        messageLayout.setVisibility(View.GONE);
    }

    @Override
    public void displayData(List<Email> emailList, Campaign campaign) {
        for (int i = 0; i < emailList.size(); i++) {
            performanceAdapter.bindViewModel(emailList.get(i));
        }
        nameTextView.setText(campaign.getName());
        dateAddedTextView.setText(Utils.getDateFormat(campaign.getDateAdded(),getContext()));
        if (campaign.isPublished()) {
            isPublishedTextView.setText(R.string.is_published);
        } else {
            isPublishedTextView.setText(R.string.not_published);
        }
        if (campaign.getPublishUp() == null) {
            publishedUpTextView.setText(R.string.not_yet_published);
        } else {
            publishedUpTextView.setText(Utils.getDateFormat(campaign.getPublishUp(),getContext()));
        }
        emailCountTextView.setText(String.valueOf(emailList.size()));
    }


    @Override
    public void onEmailClick(Email email) {
        Intent intent = new Intent(getContext(), EmailDetailActivity.class);
        intent.putExtra(EmailDetailActivity.ID_EMAIL, email.getId());
        startActivity(intent);
    }
}
