package com.kaffka.simplemap.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kaffka.simplemap.R;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (CheckConnectivity.isInternetAvailable(context)) {

            if (CheckConnectivity.isWifi(context)){
                // DO NETWORK INTENSIVE OPERATIONS HERE
            }
         } else
            Toast.makeText(context, R.string.no_internet_available, Toast.LENGTH_LONG).show();
    }
}
