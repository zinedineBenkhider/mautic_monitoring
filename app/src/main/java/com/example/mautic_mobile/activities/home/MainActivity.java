package com.example.mautic_mobile.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.home.fragments.AssetsFragment;
import com.example.mautic_mobile.activities.home.fragments.CampaignsFragment;
import com.example.mautic_mobile.activities.home.fragments.FormsFragment;
import com.example.mautic_mobile.activities.login.LoginActivity;
import com.example.mautic_mobile.utils.MenuActivity;
import com.example.mautic_mobile.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends MenuActivity {

    private BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);

        String userName = Utils.getUserNameFromPreferences(this);
        String passWord = Utils.getUserPswFromPreferences(this);
        String endPoint = Utils.getUserEndPointFromPreferences(this);
        if (userName.equals("") || passWord.equals("") || endPoint.equals("")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        bottom_navigation.setOnNavigationItemSelectedListener(listener);
        configureToolbar();
        Fragment fragment = CampaignsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case (R.id.campaigns_nav_item):
                    selectedFragment = CampaignsFragment.newInstance();
                    break;
                case (R.id.assets_nav_item):
                    selectedFragment = AssetsFragment.newInstance();
                    break;
                case (R.id.forms_nav_item):
                    selectedFragment = FormsFragment.newInstance();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };


    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTitle = findViewById(R.id.title_tool_bar);
        toolBarTitle.setText(R.string.app_name);
        AppCompatImageButton backBtn = findViewById(R.id.back_btn_tool_bar);
        backBtn.setVisibility(View.GONE);
    }


}