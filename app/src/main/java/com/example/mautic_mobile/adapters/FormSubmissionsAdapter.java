package com.example.mautic_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.api.entities.FormSubmission;
import com.example.mautic_mobile.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FormSubmissionsAdapter extends RecyclerView.Adapter<FormSubmissionsAdapter.FormSubmissionViewHolder> {
    private List<FormSubmission> formSubmissions;

    public FormSubmissionsAdapter() {
        this.formSubmissions = new ArrayList<>();

    }

    @Override
    public FormSubmissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_asset_item, parent, false);
        FormSubmissionViewHolder formSubmissionViewHolder = new FormSubmissionViewHolder(v);
        return formSubmissionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FormSubmissionViewHolder holder, int position) {
        holder.updateFormSubmission(formSubmissions.get(position));
    }

    @Override
    public int getItemCount() {
        return formSubmissions.size();
    }

    public void bindViewModel(FormSubmission submission) {
        this.formSubmissions.add(submission);
        notifyDataSetChanged();
    }

    public static class FormSubmissionViewHolder extends RecyclerView.ViewHolder {
        private TextView dateSubmittedTextView;
        private TextView leadNameTextView;
        private TextView leadPointsTextView;
        private TextView valuesTextView;
        private View view;

        public FormSubmissionViewHolder(View v) {
            super(v);
            this.view = v;
            dateSubmittedTextView = v.findViewById(R.id.title_asset_item);
            leadNameTextView = v.findViewById(R.id.lead_fullname_submission_item);
            leadPointsTextView = v.findViewById(R.id.lead_points_submission_item);
            valuesTextView = v.findViewById(R.id.values_submission_item);
        }

        public void updateFormSubmission(final FormSubmission formSubmission) {
            leadNameTextView.setText(formSubmission.getLeadFullName());
            dateSubmittedTextView.setText(Utils.getDateFormat(formSubmission.getDateSubmitted(),view.getContext()));
            String leadPoints = formSubmission.getLeadPoints() + " " + view.getResources().getString(R.string.points_label);
            leadPointsTextView.setText(leadPoints);
            valuesTextView.setText(formSubmission.getStringResults());
        }
    }
}