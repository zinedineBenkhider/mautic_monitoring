package com.example.mautic_mobile.activities.campaign_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.campaign_detail.fragments.ContactsCompaignFragment;
import com.example.mautic_mobile.activities.campaign_detail.fragments.DetailCampaignFragment;
import com.example.mautic_mobile.utils.MenuActivity;

public class CampaignDetailActivity extends MenuActivity {
    private ContactsCompaignFragment contactsCompaignFragment;
    private DetailCampaignFragment detailCampaignFragment;
    private ViewPager viewPager;
    public static String ID_CAMPAIGN_DETAIL_ACTIVITY = "com.example.mautic_mobile.activities.campaign_detail.ID_CAMPAIGN_DETAIL_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);
        viewPager = findViewById(R.id.tab_viewpager);
        contactsCompaignFragment = ContactsCompaignFragment.newInstance();
        detailCampaignFragment = DetailCampaignFragment.newInstance();
        setupViewPagerAndTabs();
        configureToolbar();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTitle = findViewById(R.id.title_tool_bar);
        toolBarTitle.setText(R.string.campaign_detail_toolbar_title);
        AppCompatImageButton backBtn = findViewById(R.id.back_btn_tool_bar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupViewPagerAndTabs() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return detailCampaignFragment;
                }
                return contactsCompaignFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return getString(R.string.title_page_detail);
                }
                return getString(R.string.title_page_contacts);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

}
