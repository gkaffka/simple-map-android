package com.kaffka.simplemap.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kaffka.simplemap.Events.EventSomethingIsNotRight;
import com.kaffka.simplemap.R;

import de.greenrobot.event.EventBus;

public abstract class ActivityBase extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        // We register the class to listen to Events
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        // We unregister the class to stop listening to Events
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.dialog_title));
        mProgressDialog.setMessage(getString(R.string.dialog_text));
    }

    ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }

    public void onEventMainThread(EventSomethingIsNotRight ev) {
        mProgressDialog.dismiss();
        showErrorToast();
    }

    public void showErrorToast() {
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_LONG).show();
    }
}
