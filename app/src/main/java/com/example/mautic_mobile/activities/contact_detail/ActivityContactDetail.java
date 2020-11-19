package com.example.mautic_mobile.activities.contact_detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.login.LoginActivity;
import com.example.mautic_mobile.api.entities.Contact;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.MenuActivity;
import com.example.mautic_mobile.utils.Utils;

public class ActivityContactDetail extends MenuActivity implements ContactDetailContract.ContactDetailView {
    private TextView fullNameTextView, locationTextView, pointsTextView, dateAddedTextView, firstActiveTextView, lastActiveTextView, companyTextView;
    public final static String ID_CONTACT_DETAIL_CONTACT = "com.example.mautic_mobile.activities.contact_detail.ID_CONTACT_DETAIL_CONTACT";
    public final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 3;
    private ProgressBar progressBar;
    private LinearLayout content;
    private AppCompatImageButton callBtn, sendMsgBtn, sendEmailBtn;
    private String numberPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        fullNameTextView = findViewById(R.id.first_last_name_detail_contact);
        locationTextView = findViewById(R.id.location_detail_contact);
        pointsTextView = findViewById(R.id.nb_points_detail_contact);
        dateAddedTextView = findViewById(R.id.date_added_detail_contact);
        firstActiveTextView = findViewById(R.id.first_active_detail_contact);
        lastActiveTextView = findViewById(R.id.last_active_detail_contact);
        companyTextView = findViewById(R.id.company_detail_contact);
        callBtn = findViewById(R.id.call_btn_contact_detail);
        sendMsgBtn = findViewById(R.id.send_msg_btn_contact_detail);
        sendEmailBtn = findViewById(R.id.send_email_btn_contact_detail);
        progressBar = findViewById(R.id.contact_detail_progress_bar);
        content = findViewById(R.id.content_detail_contact);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int idContact = getIntent().getIntExtra(ID_CONTACT_DETAIL_CONTACT, -1);
        String userName = Utils.getUserNameFromPreferences(this);
        String passWord = Utils.getUserPswFromPreferences(this);
        String endPoint = Utils.getUserEndPointFromPreferences(this);
        MauticClient mauticClient = new MauticClient(userName, passWord, endPoint);
        ContactDetailPresenterImpl contactDetailPresenter = new ContactDetailPresenterImpl(this, mauticClient);
        contactDetailPresenter.onActivityLoaded(idContact);
        configureToolbar();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolBarTitle = findViewById(R.id.title_tool_bar);
        toolBarTitle.setText(R.string.contact_detail_toolbar_title);
        AppCompatImageButton backBtn = findViewById(R.id.back_btn_tool_bar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startActivitySendMessage(String number) {
        if (!number.equals("")) {
            try {
                Intent messageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + number));
                messageIntent.putExtra("sms_body", "");
                startActivity(messageIntent);
            }
            catch (Exception e){
                    Toast invalidValues = Toast.makeText(this, R.string.msg_phone_number_non_valid, Toast.LENGTH_LONG);
                    invalidValues.show();
                }
        } else {
            Toast invalidValues = Toast.makeText(this, R.string.msg_phone_number_not_specified, Toast.LENGTH_LONG);
            invalidValues.show();
        }
    }

    private void startActivitySendEmail(String email) {
        if (!email.isEmpty()) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", email, null));

            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(emailIntent, getString(R.string.choose_mail_app)));
            } else {
            }
        } else {
            Toast invalidValues = Toast.makeText(this, R.string.msg_email_not_specified, Toast.LENGTH_LONG);
            invalidValues.show();
        }
    }

    private void startActivityCall(String number) {
        if (!number.equals("")) {
            try{
                this.numberPhone = number;
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    try {
                        startActivity(callIntent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch(Exception e){
                Toast invalidValues = Toast.makeText(this, R.string.msg_phone_number_non_valid, Toast.LENGTH_LONG);
                invalidValues.show();
            }

        } else {
            Toast invalidValues = Toast.makeText(this, R.string.msg_phone_number_not_specified, Toast.LENGTH_LONG);
            invalidValues.show();
        }
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
    public void setDataToFields(Contact contact) {


        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityCall(contact.getMobileNumber());
            }
        });
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivitySendMessage(contact.getMobileNumber());
            }
        });
        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivitySendEmail(contact.getEmail());
            }
        });

        fullNameTextView.setText(contact.getFirstName() + " " + contact.getLastName());
        if (contact.getCompany() == null) {
            companyTextView.setText("Not specified");
        } else {
            companyTextView.setText(contact.getCompany());
        }

        if (contact.getCountry() == null) {
            locationTextView.setText(R.string.value_not_specified);
        } else {
            locationTextView.setText(String.valueOf(contact.getCountry()));
        }
        pointsTextView.setText(String.valueOf(contact.getPoints()));
        dateAddedTextView.setText(Utils.getDateFormat(contact.getDateAdded(),this));
        if (contact.getFirstActive() == null) {
            firstActiveTextView.setText(R.string.contact_never_active);
        } else {
            firstActiveTextView.setText(Utils.getDateFormat(contact.getFirstActive(),this));
        }
        if (contact.getLastActive() == null) {
            lastActiveTextView.setText(R.string.contact_never_active);
        } else {
            lastActiveTextView.setText(Utils.getDateFormat(contact.getLastActive(),this));
        }

    }

    @Override
    public void showContent() {
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        content.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityCall(numberPhone);
                }
            }
        }
    }
}
