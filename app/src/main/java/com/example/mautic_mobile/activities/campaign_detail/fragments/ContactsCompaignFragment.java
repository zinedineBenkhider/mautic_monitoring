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
import com.example.mautic_mobile.activities.campaign_detail.ContactsActionInterface;
import com.example.mautic_mobile.activities.campaign_detail.presenter.ContactsContract;
import com.example.mautic_mobile.activities.campaign_detail.presenter.ContactsPresenterImpl;
import com.example.mautic_mobile.activities.contact_detail.ActivityContactDetail;
import com.example.mautic_mobile.adapters.ContactsAdapter;
import com.example.mautic_mobile.api.entities.Contact;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.Utils;

import java.util.List;
public class ContactsCompaignFragment extends Fragment implements ContactsContract.ContactView, ContactsActionInterface {


    private static ContactsCompaignFragment INSTANCE = null;
    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayout contentLayout;
    private LinearLayout messageLayout;
    private TextView messageTextView;
    private ContactsAdapter contactsAdapter;
    private ProgressBar progressBar;
    private ContactsCompaignFragment() {
    }

    public static ContactsCompaignFragment newInstance() {
        if (INSTANCE==null){
            return new ContactsCompaignFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_campaign_contacts, container, false);
        INSTANCE=this;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar=rootView.findViewById(R.id.campaign_contacts_progress_bar);
        contentLayout=rootView.findViewById(R.id.content_campaign_contact);
        messageLayout=rootView.findViewById(R.id.no_element_linear_layout);
        messageTextView=rootView.findViewById(R.id.message_no_elements);
        String userName= Utils.getUserNameFromPreferences(getContext());
        String passWord= Utils.getUserPswFromPreferences(getContext());
        String endPoint= Utils.getUserEndPointFromPreferences(getContext());
        MauticClient mauticClient=new MauticClient(userName,passWord,endPoint);
        ContactsPresenterImpl contactsPresenter=new ContactsPresenterImpl(this,mauticClient);
        recyclerView =rootView.findViewById(R.id.recycler_view_contacts);
        int campaignId=getActivity().getIntent().getIntExtra(CampaignDetailActivity.ID_CAMPAIGN_DETAIL_ACTIVITY,-1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        contactsAdapter=new ContactsAdapter(this);
        recyclerView.setAdapter(contactsAdapter);
        contactsPresenter.onActivityLoaded(campaignId);
    }

    @Override
    public void onContactClick(Contact contact) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ActivityContactDetail.class);
        intent.putExtra(ActivityContactDetail.ID_CONTACT_DETAIL_CONTACT, contact.getId());
        startActivity(intent);
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
        messageTextView.setText(R.string.msg_no_contact);
    }

    @Override
    public void hideMessageLayout() {
        messageLayout.setVisibility(View.GONE);
    }

    @Override
    public void setDataToAdapter(List<Contact> contactList) {
        for (int i=0;i<contactList.size();i++){
            contactsAdapter.bindViewModel(contactList.get(i));
        }
    }


}
