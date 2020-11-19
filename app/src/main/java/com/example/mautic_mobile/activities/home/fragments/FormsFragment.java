package com.example.mautic_mobile.activities.home.fragments;

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
import com.example.mautic_mobile.activities.form_detail.FormDetailActivity;
import com.example.mautic_mobile.activities.home.presenter.FormFragmentPresenterImpl;
import com.example.mautic_mobile.activities.home.presenter.interfaces.FormActionInterface;
import com.example.mautic_mobile.activities.home.presenter.interfaces.FormsFragmentContract;
import com.example.mautic_mobile.adapters.FormAdapter;
import com.example.mautic_mobile.api.entities.Form;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.Utils;

import java.util.List;

import static com.example.mautic_mobile.activities.form_detail.FormDetailActivity.DATE_ADDED_FORM_DETAIL_ACTIVITY;
import static com.example.mautic_mobile.activities.form_detail.FormDetailActivity.ID_FORM_DETAIL_ACTIVITY;
import static com.example.mautic_mobile.activities.form_detail.FormDetailActivity.IS_PUBLISHED_FORM_DETAIL_ACTIVITY;
import static com.example.mautic_mobile.activities.form_detail.FormDetailActivity.NAME_FORM_DETAIL_ACTIVITY;
import static com.example.mautic_mobile.activities.form_detail.FormDetailActivity.PUBLISHED_UP_FORM_DETAIL_ACTIVITY;


public class FormsFragment extends Fragment implements FormsFragmentContract.FormsFragmentView, FormActionInterface {
    private static FormsFragment INSTANCE = null;
    private FormAdapter formAdapter;
    private RecyclerView recyclerView;
    private View rootView;
    private ProgressBar progressBar;
    private LinearLayout contentLayout;
    private LinearLayout messageLayout;
    private TextView messageTextView;

    private FormsFragment() {
    }

    public static FormsFragment newInstance() {
        if (INSTANCE == null) {
            return new FormsFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_form, container, false);
        INSTANCE = this;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recycler_view_form);
        initRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar_form);
        contentLayout = rootView.findViewById(R.id.content_forms_fragment);
        messageLayout = rootView.findViewById(R.id.no_element_linear_layout);
        messageTextView = rootView.findViewById(R.id.message_no_elements);
        String userName = Utils.getUserNameFromPreferences(getContext());
        String passWord = Utils.getUserPswFromPreferences(getContext());
        String endPoint = Utils.getUserEndPointFromPreferences(getContext());
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        FormFragmentPresenterImpl fragmentPresenter = new FormFragmentPresenterImpl(this, mauticClient);
        fragmentPresenter.onActivityCreated();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        formAdapter = new FormAdapter(this);
        recyclerView.setAdapter(formAdapter);
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
    public void setDataToAdapter(List<Form> forms) {
        for (int i = 0; i < forms.size(); i++) {
            formAdapter.bindViewModel(forms.get(i));
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
        messageTextView.setText(R.string.msg_no_form);
    }

    @Override
    public void hideMessageLayout() {
        messageLayout.setVisibility(View.GONE);
    }


    @Override
    public void onFormClick(Form form) {
        Intent intent = new Intent(getActivity(), FormDetailActivity.class);
        intent.putExtra(ID_FORM_DETAIL_ACTIVITY, form.getId());
        intent.putExtra(NAME_FORM_DETAIL_ACTIVITY, form.getName());
        intent.putExtra(DATE_ADDED_FORM_DETAIL_ACTIVITY,Utils.getDateFormat(form.getDateAdded(),getContext()));
        intent.putExtra(PUBLISHED_UP_FORM_DETAIL_ACTIVITY, Utils.getDateFormat(form.getPublishUp(),getContext()));
        intent.putExtra(IS_PUBLISHED_FORM_DETAIL_ACTIVITY, form.getPublished());
        startActivity(intent);
    }
}
