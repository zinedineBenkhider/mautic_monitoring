package com.example.mautic_mobile.api.services;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitMauticService {

    private String endPoint;
    private static InitMauticService INSTANCE = null;
    private MauticService mauticService;

    private InitMauticService(String userName, String password, String endPoint) {
        this.endPoint = endPoint;
        OkHttpClient okHttpClient = getHttpClient(userName, password);
        this.mauticService = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MauticService.class);
    }

    public static InitMauticService getInstance(String userName, String password, String endPoint) {
        if (INSTANCE == null) {
            INSTANCE = new InitMauticService(userName, password, endPoint);
        }
        return INSTANCE;
    }

    public MauticService getMauticService() {
        return this.mauticService;
    }

    private OkHttpClient getHttpClient(String userName, String password) {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request request = chain.request();
                        String credential = Credentials.basic(userName, password);
                        final Request newRequest = request.newBuilder()
                                .addHeader("Authorization", credential)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        return okHttpClient;
    }


}
