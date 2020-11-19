package com.example.mautic_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.campaign_detail.EmailActionInterface;
import com.example.mautic_mobile.api.entities.Email;
import com.example.mautic_mobile.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.PerformanceViewHolder> {
    private List<Email> performanceViewModels;
    private EmailActionInterface emailActionInterface;

    public EmailAdapter(EmailActionInterface emailActionInterface) {
        this.performanceViewModels = new ArrayList<>();
        this.emailActionInterface = emailActionInterface;
    }

    @Override
    public EmailAdapter.PerformanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_email_item, parent, false);
        PerformanceViewHolder performanceViewHolder = new PerformanceViewHolder(v);
        return performanceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EmailAdapter.PerformanceViewHolder holder, int position) {
        holder.updateCampaign(performanceViewModels.get(position), emailActionInterface);
    }

    @Override
    public int getItemCount() {
        return performanceViewModels.size();
    }

    public void bindViewModel(Email email) {
        this.performanceViewModels.add(email);
        notifyDataSetChanged();
    }

    public static class PerformanceViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView dateAddedTextView;
        private TextView nbSentTextView;
        private View view;

        public PerformanceViewHolder(View v) {
            super(v);
            this.view = v;
            nameTextView = v.findViewById(R.id.name_email_item);
            dateAddedTextView = v.findViewById(R.id.date_added_email_item);
            nbSentTextView = v.findViewById(R.id.nb_sent_email_item);
        }

        public void updateCampaign(final Email email, EmailActionInterface emailActionInterface) {
            nameTextView.setText(email.getName());
            dateAddedTextView.setText(Utils.getDateFormat(email.getDateAdded(),view.getContext()));
            String sentCount = email.getSentCount() + view.getResources().getString(R.string.nb_email_sent_label);
            nbSentTextView.setText(sentCount);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emailActionInterface.onEmailClick(email);
                }
            });
        }
    }
}
