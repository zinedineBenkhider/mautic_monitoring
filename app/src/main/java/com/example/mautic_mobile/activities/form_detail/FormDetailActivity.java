package com.example.mautic_mobile.activities.form_detail;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.adapters.FormSubmissionsAdapter;
import com.example.mautic_mobile.api.entities.FormSubmission;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.MenuActivity;
import com.example.mautic_mobile.utils.Utils;

import java.util.List;

public class FormDetailActivity extends MenuActivity implements FormDetailContract.FormDetailView {
    private ProgressBar progressBar;
    private FormSubmissionsAdapter formSubmissionsAdapter;
    public final static String ID_FORM_DETAIL_ACTIVITY = "com.example.mautic_mobile.activities.form_detail.ID_FORM_DETAIL_ACTIVITY";
    public final static String PUBLISHED_UP_FORM_DETAIL_ACTIVITY = "com.example.mautic_mobile.activities.form_detail.PUBLISHED_UP_FORM_DETAIL_ACTIVITY";
    public final static String NAME_FORM_DETAIL_ACTIVITY = "com.example.mautic_mobile.activities.form_detail.NAME_FORM_DETAIL_ACTIVITY";
    public final static String IS_PUBLISHED_FORM_DETAIL_ACTIVITY = "com.example.mautic_mobile.activities.form_detail.IS_PUBLISHED_FORM_DETAIL_ACTIVITY";

    public final static String DATE_ADDED_FORM_DETAIL_ACTIVITY = "com.example.mautic_mobile.activities.form_detail.DATE_ADDED_FORM_DETAIL_ACTIVITY";

    private LinearLayout contentLayout;
    private LinearLayout messageLayout;
    private TextView messageTextView, nameTextView, dateAddedTextView, publishedUpTextView, isPublishedTextView, submissionsTextView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_detail);
        contentLayout = findViewById(R.id.content_detail_form);
        messageLayout = findViewById(R.id.content_no_submissions);
        messageTextView = findViewById(R.id.message_no_elements);
        progressBar = findViewById(R.id.form_submissions_progress_bar);
        recyclerView = findViewById(R.id.recycler_view_submissions);
        nameTextView = findViewById(R.id.name_form_detail_form);
        dateAddedTextView = findViewById(R.id.date_added_detail_form);
        publishedUpTextView = findViewById(R.id.published_up_detail_form);
        isPublishedTextView = findViewById(R.id.is_published_detail_form);
        submissionsTextView = findViewById(R.id.submissions_count_detail_form);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        formSubmissionsAdapter = new FormSubmissionsAdapter();
        recyclerView.setAdapter(formSubmissionsAdapter);
        Intent intent = getIntent();
        int idForm = intent.getIntExtra(ID_FORM_DETAIL_ACTIVITY, -1);
        String nameForm = intent.getStringExtra(NAME_FORM_DETAIL_ACTIVITY);
        String publishedUpForm = intent.getStringExtra(PUBLISHED_UP_FORM_DETAIL_ACTIVITY);
        String dateAddedForm=intent.getStringExtra(DATE_ADDED_FORM_DETAIL_ACTIVITY);
        Boolean isPublishedForm = intent.getBooleanExtra(IS_PUBLISHED_FORM_DETAIL_ACTIVITY, false);

        String userName = Utils.getUserNameFromPreferences(this);
        String passWord = Utils.getUserPswFromPreferences(this);
        String endPoint = Utils.getUserEndPointFromPreferences(this);
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        FormDetailPresenterImpl formSubmissionsPresenter = new FormDetailPresenterImpl(mauticClient, this);
        formSubmissionsPresenter.onActivityLoaded(idForm);
        configureToolbar();
        initDetail(nameForm, publishedUpForm, dateAddedForm,isPublishedForm);
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTitle = findViewById(R.id.title_tool_bar);
        toolBarTitle.setText("Form");
        AppCompatImageButton backBtn = findViewById(R.id.back_btn_tool_bar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void initDetail(String nameForm, String publishedUpForm, String dateAddedForm, Boolean isPublishedForm) {
        nameTextView.setText(nameForm);
        if (publishedUpForm.equals("")) {
            publishedUpTextView.setText(getString(R.string.not_yet_published));
        } else {
            publishedUpTextView.setText(publishedUpForm);
        }
        if (isPublishedForm) {
            isPublishedTextView.setText(getString(R.string.is_published));
        } else {
            isPublishedTextView.setText(getString(R.string.not_published));
        }
        submissionsTextView.setText("0");
        dateAddedTextView.setText(dateAddedForm);
    }

    @Override
    public void setDataToAdapter(List<FormSubmission> formSubmissions) {
        for (int i = 0; i < formSubmissions.size(); i++) {
            formSubmissionsAdapter.bindViewModel(formSubmissions.get(i));
        }
        submissionsTextView.setText(String.valueOf(formSubmissions.size()));
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
        messageTextView.setText(R.string.msg_no_submissions);
    }

    @Override
    public void hideMessageLayout() {
        messageLayout.setVisibility(View.GONE);
    }
}