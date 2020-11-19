package com.example.mautic_mobile.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mautic_mobile.R;
import com.example.mautic_mobile.activities.home.MainActivity;
import com.example.mautic_mobile.api.services.MauticClient;
import com.example.mautic_mobile.utils.Encryption;
import com.example.mautic_mobile.utils.Utils;

public class LoginActivity extends AppCompatActivity implements LogInContract.LoginView {
    private EditText emailEditText, passwordEditText, urlEditText;
    private Button btnConnection;
    private String userName, password, endPoint;
    private ProgressBar progressBar;
    private ImageButton btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.user_email_login);
        passwordEditText = findViewById(R.id.user_password_login);
        urlEditText = findViewById(R.id.url_login);
        btnConnection = findViewById(R.id.btn_connection_login);
        progressBar = findViewById(R.id.progressBar_login);
        btnCancel = findViewById(R.id.button_cancel_login);
        setBtnEvents();
    }

    private void setBtnEvents() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LogInContract.LoginView loginView = this;
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                //endPoint = "https://";
                endPoint = urlEditText.getText().toString();
                System.out.println(endPoint);
                MauticClient mauticClient = new MauticClient(userName, password, endPoint);
                LogInPresenterImpl logInPresenter = new LogInPresenterImpl(mauticClient, loginView);
                logInPresenter.login();
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
    public void disableConnectionBtn() {
        btnConnection.setActivated(false);
    }

    @Override
    public void activateConnectionBtn() {
        btnConnection.setActivated(true);
    }

    @Override
    public void resultConnection(Boolean success,String msgError) {
        if (success) {
           password= Encryption.encrypt(password);
            Utils.editIdentifiersPreferences(this,userName,password,endPoint);
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast invalidValues = Toast.makeText(this, msgError, Toast.LENGTH_LONG);
            invalidValues.show();
        }
    }
}
