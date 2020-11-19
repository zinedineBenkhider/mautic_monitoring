package com.example.mautic_mobile.activities.email_detail;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.api.entities.Email;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.MenuActivity;
import com.example.mautic_mobile.utils.Utils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EmailDetailActivity extends MenuActivity implements EmailDetailContract.EmailDetailView {
    private TextView nameTextView, publishUpTextView, dateAddedTextView, sentCountTextView, subjectTextView;
    private LinearLayout content,pieChartLayout;
    private ProgressBar progressBar;
    private PieChart pieChartEmailClicks, pieChartEmailUnsubscribe;
    public static String ID_EMAIL = "com.example.mautic_mobile.activities.email_detail.ID_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_detail);
        nameTextView = findViewById(R.id.name_email_detail);
        publishUpTextView = findViewById(R.id.published_up_email_detail);
        dateAddedTextView = findViewById(R.id.date_added_email_detail);
        sentCountTextView = findViewById(R.id.sent_count_email_detail);
        subjectTextView = findViewById(R.id.subject_email_detail);
        pieChartLayout=findViewById(R.id.pie_chart_layout_detail_email);
        content = findViewById(R.id.content_email_detail);
        progressBar = findViewById(R.id.email_detail_progress_bar);
        int emailId = getIntent().getIntExtra(ID_EMAIL, -1);
        String userName = Utils.getUserNameFromPreferences(this);
        String passWord = Utils.getUserPswFromPreferences(this);
        String endPoint = Utils.getUserEndPointFromPreferences(this);
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        EmailDetailPresenterImpl emailDetailPresenter = new EmailDetailPresenterImpl(mauticClient, this);
        emailDetailPresenter.onActivityLoaded(emailId);
        configureToolbar();
        pieChartEmailClicks = findViewById(R.id.piechart_sent_email_detail);
        pieChartEmailUnsubscribe = findViewById(R.id.piechart_unsubscribes_email_detail);

    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTitle = findViewById(R.id.title_tool_bar);
        toolBarTitle.setText("Email");
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

    @Override
    public void setDataToAdapter(int submissions, Email email) {
        nameTextView.setText(email.getName());
        if (email.getPublishUp() != null) {
            publishUpTextView.setText(Utils.getDateFormat(email.getPublishUp(),this));
        } else {
            publishUpTextView.setText(R.string.not_yet_published_up);
        }
        if (email.getDateAdded() != null) {
            dateAddedTextView.setText(Utils.getDateFormat(email.getDateAdded(),this));
        }
        sentCountTextView.setText(String.valueOf(email.getSentCount()));
        subjectTextView.setText(email.getSubject());
        int clicks = email.getReadCount();
        int unClicks = email.getSentCount() - clicks;

        int notUnsubscribe = email.getSentCount() - submissions;
        int total = email.getSentCount();

        showPieChart(pieChartEmailClicks, clicks, unClicks, getString(R.string.email_clicks_legend), getString(R.string.email_not_click_legend), total, getString(R.string.email_chart_description));
        showPieChart(pieChartEmailUnsubscribe, notUnsubscribe, submissions, getString(R.string.email_not_unsubscribe_legend), getString(R.string.email_unsubscribe_legend), total, getString(R.string.email_unsubscribe_chart_description));
    }

    public void showPieChart(PieChart pieChart, float positivePart, float negativePart, String positivePartLabel, String negativePartLabel, int total, String description) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "";

        float positivePartPercent = 0;
        float negativePartPercent=0;
        if(total>0){
            positivePartPercent = positivePart / total;
            negativePartPercent = negativePart / total;
        }
        List<String> types = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
        types.add(positivePartLabel + "(" + df.format(positivePartPercent * 100) + getString(R.string.percent_symbole) + ")");
        types.add(negativePartLabel + "(" + df.format(negativePartPercent * 100) + getString(R.string.percent_symbole) + ")");
        values.add((int) positivePart);
        values.add((int) negativePart);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ResourcesCompat.getColor(getResources(), R.color.color_pie_positive_part, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.color_pie_negative_part, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        for (int i = 0; i < values.size(); i++) {
            pieEntries.add(new PieEntry(values.get(i), types.get(i)));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieChart.setDrawSliceText(false);
        pieChart.getDescription().setText(description);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void showContentLayout() {
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentLayout() {
        content.setVisibility(View.GONE);
    }

    private void showPieChartLayout(){
        pieChartLayout.setVisibility(View.VISIBLE);
    }
    private void hidePieChartLayout(){
        pieChartLayout.setVisibility(View.GONE);
    }
}