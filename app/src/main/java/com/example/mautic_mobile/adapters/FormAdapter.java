package com.example.mautic_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.home.presenter.interfaces.FormActionInterface;
import com.example.mautic_mobile.api.entities.Form;
import com.example.mautic_mobile.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.FormViewHolder> {
    private List<Form> forms;
    private FormActionInterface formActionInterface;

    public FormAdapter(FormActionInterface formActionInterface) {
        this.forms = new ArrayList<>();
        this.formActionInterface = formActionInterface;
    }

    @Override
    public FormViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_form_item, parent, false);
        FormViewHolder formViewHolder = new FormViewHolder(v);
        return formViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FormViewHolder holder, int position) {
        holder.updateForm(forms.get(position), formActionInterface);
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public void bindViewModel(Form form) {
        this.forms.add(form);
        notifyDataSetChanged();
    }

    public static class FormViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView, dateTextView,publishedStateTextView;
        private Switch isPublishedSwitch;
        private View view;

        public FormViewHolder(View v) {
            super(v);
            this.view = v;
            nameTextView = v.findViewById(R.id.name_form_item);
            dateTextView = v.findViewById(R.id.date_added_form_item);
            isPublishedSwitch = v.findViewById(R.id.is_published_form_item);
            publishedStateTextView=v.findViewById(R.id.published_state_form_item);

        }

        public void updateForm(final Form form, FormActionInterface formActionInterface) {
            nameTextView.setText(form.getName());
            dateTextView.setText(Utils.getDateFormat(form.getDateAdded(),view.getContext()));
            isPublishedSwitch.setChecked(form.getPublished());
            isPublishedSwitch.setEnabled(false);
            if(form.getPublished()){
                publishedStateTextView.setText(R.string.published_state_public);
            }
            else {
                publishedStateTextView.setText(R.string.published_state_private);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    formActionInterface.onFormClick(form);
                }
            });
        }
    }
}

