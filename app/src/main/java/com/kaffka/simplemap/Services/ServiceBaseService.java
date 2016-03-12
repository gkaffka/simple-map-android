package com.kaffka.simplemap.Services;

import android.util.Log;

import com.kaffka.simplemap.ApiConnectionInterface;
import com.kaffka.simplemap.Network.ConnectionRetryInterceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

abstract class ServiceBaseService {

    private final ApiConnectionInterface mServiceInterface;

    ServiceBaseService() {

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setRetryOnConnectionFailure(true);
        okHttpClient.setFollowRedirects(false);
        okHttpClient.interceptors().add(new ConnectionRetryInterceptor());

        RestAdapter mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConnectionInterface.BASE_API_URL)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.d("SIMPLE_MAP_APP", msg);
                    }
                })
                .build();

        mServiceInterface = mRestAdapter.create(ApiConnectionInterface.class);
    }

    ApiConnectionInterface getServiceInterface() {
        return mServiceInterface;
    }
}
