package com.example.mautic_mobile.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.home.presenter.interfaces.CampaignActionInterface;
import com.example.mautic_mobile.api.entities.Campaign;
import com.example.mautic_mobile.utils.Utils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder> {
    private List<Campaign> campaignsList;
    private CampaignActionInterface campaignActionInterface;

    public CampaignAdapter(CampaignActionInterface campaignActionInterface) {
        this.campaignsList = new ArrayList<>();
        this.campaignActionInterface = campaignActionInterface;
    }

    @Override
    public CampaignAdapter.CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_campaign_item, parent, false);
        CampaignViewHolder campaignViewHolder = new CampaignViewHolder(v);
        return campaignViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CampaignViewHolder holder, int position) {
        holder.updateCampaign(campaignsList.get(position), campaignActionInterface);

    }

    @Override
    public int getItemCount() {
        return campaignsList.size();
    }


    public void bindViewModel(Campaign campaign) {
        this.campaignsList.add(campaign);
        notifyDataSetChanged();
    }

    public static class CampaignViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView, dateTextView, publishedStateTextView;

        private Switch isPublishedSwitch;

        private View view;

        public CampaignViewHolder(View v) {
            super(v);
            this.view = v;
            nameTextView = v.findViewById(R.id.name_campaign_item);
            dateTextView = v.findViewById(R.id.date_added_campaign_item);
            publishedStateTextView = v.findViewById(R.id.published_state_campaign_item);
            isPublishedSwitch = v.findViewById(R.id.is_published_campaign_item);
        }

        public void updateCampaign(final Campaign campaign, CampaignActionInterface campaignActionInterface) {
            nameTextView.setText(campaign.getName());
            dateTextView.setText(Utils.getDateFormat(campaign.getDateAdded(),view.getContext()));
            isPublishedSwitch.setChecked(campaign.isPublished());
            isPublishedSwitch.setEnabled(false);
            if(campaign.isPublished()){
                publishedStateTextView.setText(R.string.published_state_public);
            }
            else {
                publishedStateTextView.setText(R.string.published_state_private);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    campaignActionInterface.onCampaignClick(campaign);
                }
            });
        }
    }
}

