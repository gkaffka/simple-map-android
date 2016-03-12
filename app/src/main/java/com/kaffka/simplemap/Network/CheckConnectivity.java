package com.kaffka.simplemap.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class CheckConnectivity {

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return isInternetAvailable(context) && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }
}