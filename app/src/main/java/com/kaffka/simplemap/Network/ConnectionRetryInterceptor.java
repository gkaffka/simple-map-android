package com.kaffka.simplemap.Network;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ConnectionRetryInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        boolean responseOK = false;
        int tryCount = 0;

        int tryCountTotal = 4;
        while (!responseOK && tryCount < tryCountTotal) {
            try {
                response = chain.proceed(request);
                responseOK = response.isSuccessful();
            } catch (Exception e) {
                Log.d("intercept", "Request is not successful - " + tryCount);
            } finally {
                tryCount++;
            }
        }
        return response;
    }
}
