package com.example.mautic_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.campaign_detail.ContactsActionInterface;
import com.example.mautic_mobile.api.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.CampaignViewHolder> {
    private List<Contact> campaignsList;
    private ContactsActionInterface contactsActionInterface;

    public ContactsAdapter(ContactsActionInterface contactsActionInterface) {
        this.campaignsList = new ArrayList<>();
        this.contactsActionInterface = contactsActionInterface;
    }

    @Override
    public ContactsAdapter.CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_contact_item, parent, false);
        ContactsAdapter.CampaignViewHolder campaignViewHolder = new ContactsAdapter.CampaignViewHolder(v);
        return campaignViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsAdapter.CampaignViewHolder holder, int position) {
        holder.updateCompaign(campaignsList.get(position), contactsActionInterface);
    }

    @Override
    public int getItemCount() {
        return campaignsList.size();
    }

    public void bindViewModel(Contact contact) {
        this.campaignsList.add(contact);
        notifyDataSetChanged();
    }

    public static class CampaignViewHolder extends RecyclerView.ViewHolder {
        private TextView firstNameTextView;
        private TextView lastNameTextView;
        private TextView nbPointsTextView;
        private View view;

        public CampaignViewHolder(View v) {
            super(v);
            this.view = v;
            firstNameTextView = v.findViewById(R.id.firstname_contact_item);
            lastNameTextView = v.findViewById(R.id.lastname_contact_item);
            nbPointsTextView = v.findViewById(R.id.nb_points_contact_item);
        }

        public void updateCompaign(final Contact contact, ContactsActionInterface contactsActionInterface) {
            firstNameTextView.setText(contact.getFirstName());
            lastNameTextView.setText(contact.getLastName());
            nbPointsTextView.setText(String.valueOf(contact.getPoints()) + " points");
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contactsActionInterface.onContactClick(contact);
                }
            });
        }
    }
}