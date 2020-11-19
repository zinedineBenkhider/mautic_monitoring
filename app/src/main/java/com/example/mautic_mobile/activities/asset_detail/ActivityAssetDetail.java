package com.example.mautic_mobile.activities.asset_detail;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.api.entities.Asset;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.MenuActivity;
import com.example.mautic_mobile.utils.Utils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ActivityAssetDetail extends MenuActivity implements ContractAssetDetail.AssetDetailView {
    private TextView nameTextView, dateAddedTextView,publishedUpTextView, extensionTextView, urlTextView;
    public final static String ID_ASSET_DETAIL_ASSET = "com.example.mautic_mobile.activities.asset_detail.ID_ASSET_DETAIL_ASSET";
    private LinearLayout content;
    private ProgressBar progressBar;
    private String SET_LABEL;
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);
        nameTextView = findViewById(R.id.name_asset_detail);
        dateAddedTextView=findViewById(R.id.date_added_asset_detail);
        publishedUpTextView = findViewById(R.id.published_up_asset_detail);
        extensionTextView = findViewById(R.id.extension_asset_detail);
        urlTextView = findViewById(R.id.url_download_asset_detail);
        barChart = findViewById(R.id.barChart_asset_detail);
        content = findViewById(R.id.content_asset_detail);
        progressBar = findViewById(R.id.asset_detail_progress_bar);
        String userName = Utils.getUserNameFromPreferences(this);
        String passWord = Utils.getUserPswFromPreferences(this);
        String endPoint = Utils.getUserEndPointFromPreferences(this);
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        int idAsset = getIntent().getIntExtra(ID_ASSET_DETAIL_ASSET, -1);
        AssetDetailPresenterImpl assetDetailPresenter = new AssetDetailPresenterImpl(this, mauticClient);
        assetDetailPresenter.onActivityLoaded(idAsset);
        configureToolbar();
    }

    private void createChartData(Asset asset) {
        List<BarEntry> downloads = new ArrayList<>();
        downloads.add(new BarEntry(1, asset.getUniqueDownloadCount()));
        downloads.add(new BarEntry(2, asset.getDownloadCount()));
        /*downloads.add(new BarEntry(1, 10));
        downloads.add(new BarEntry(2, 15));*/
        BarDataSet bardataset = new BarDataSet(downloads,"");
        barChart.animateY(400);
        BarData data = new BarData(bardataset);
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ResourcesCompat.getColor(getResources(), R.color.color_bar_unique_download, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.color_bar_all_download, null));

        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add(getString(R.string.downloads_unique_count_legend));
        xAxisValues.add(getString(R.string.downloads_unique_count_legend));
        xAxisValues.add(getString(R.string.downloads_all_count_legend));
        bardataset.setColors(colors);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                if (value >= 0 && value < xAxisValues.size()) {
                    return xAxisValues.get((int) value);
                }
                return "";
            }
        });
        barChart.getDescription().setEnabled(false);
        barChart.setData(data);
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTitle = findViewById(R.id.title_tool_bar);
        toolBarTitle.setText(R.string.toolbar_title_asset_detail);
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
    public void setDataToFields(Asset asset) {
        nameTextView.setText(asset.getTitle());
        extensionTextView.setText(asset.getExtension().toUpperCase());
        dateAddedTextView.setText(Utils.getDateFormat(asset.getDateAdded(),this));
        if (asset.getPublishUp() != null) {
            publishedUpTextView.setText(Utils.getDateFormat(asset.getPublishUp(),this));
        } else {
            publishedUpTextView.setText(getString(R.string.not_yet_published));
        }
        if (asset.getDownloadUrl() != null) {
            urlTextView.setText(asset.getDownloadUrl());
        } else {
            urlTextView.setText(getString(R.string.not_founded));
        }
        createChartData(asset);
    }

    @Override
    public void showContent() {
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        content.setVisibility(View.INVISIBLE);
    }

}