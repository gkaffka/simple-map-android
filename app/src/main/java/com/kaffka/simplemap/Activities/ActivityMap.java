package com.kaffka.simplemap.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kaffka.simplemap.Events.EventDataFound;
import com.kaffka.simplemap.Models.Request;
import com.kaffka.simplemap.Models.Unit;
import com.kaffka.simplemap.R;
import com.kaffka.simplemap.Services.ServiceGetDataFromApi;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityMap extends ActivityBase implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer)
    DrawerLayout mDrawer;
    @Bind(R.id.toolbar_actionbar)
    Toolbar mToolbar;
    @Bind(R.id.navigation_view)
    NavigationView mNavigation;
    private boolean isMapReady;
    private GoogleMap mMap;
    private final int PERMISSION_REQUEST = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavigationDrawer();
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        initMap(map);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.submenu:
                callService(menuItem, new Request("2", "-23", "-46", "28|", "São Paulo - SP, Brazil"), R.string.item_title);
                return true;
            case R.id.another_submenu:
                callService(menuItem, new Request("2", "-23", "-46", "15|", "São Paulo - SP, Brazil"), R.string.another_item_title);
                return true;
            default:
                return true;
        }
    }

    private void callService(MenuItem menuItem, Request request, int title) {
        menuItem.setChecked(true);
        getProgressDialog().show();
        updatesToolbarTitle(title);
        new ServiceGetDataFromApi().getDataFromApi(request);
        hideDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                startActivity(new Intent(ActivityMap.this, ActivitySimpleList.class));
                return true;
            default:
                finish();
                return true;
        }
    }

    public void onEventMainThread(EventDataFound ev) {
        getProgressDialog().dismiss();
        if (!isMapReady || mMap == null || ev.getUnits().getUnitList() == null || ev.getUnits().getUnitList().isEmpty()) {
            Toast.makeText(this, R.string.generic_error, Toast.LENGTH_LONG).show();
            return;
        }
        loadMapPins(ev.getUnits().getUnitList());
    }

    private void loadMapPins(List<Unit> units) {
        mMap.clear();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Unit u : units) {
            mMap.addMarker(new MarkerOptions()
                    .title(u.getNome())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_pin))
                    .snippet(u.buildSnippet())
                    .position(new LatLng(u.getGeo().getLatitude(), u.getGeo().getLongitude())));
            builder.include(new LatLng(u.getGeo().getLatitude(), u.getGeo().getLongitude()));
        }

        LatLngBounds bounds = builder.build();
        int padding = (int) (16 * getResources().getDisplayMetrics().density);
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }

    private void hideDrawer() {
        if (mDrawer.isDrawerOpen(GravityCompat.START))
            mDrawer.closeDrawer(GravityCompat.START);
    }

    private void initMap(GoogleMap map) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                map.setMyLocationEnabled(true);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST);
            }
        } else {
            map.setMyLocationEnabled(true);
        }

        isMapReady = true;
        this.mMap = map;

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(final Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.info_window, null);
                ((TextView) v.findViewById(R.id.txt_snippet)).setText(marker.getSnippet());
                ((TextView) v.findViewById(R.id.txt_title)).setText(marker.getTitle());

                return v;
            }
        });
        loadFirstItem();
    }

    private void initNavigationDrawer() {
        setSupportActionBar(mToolbar);
        mNavigation.setNavigationItemSelectedListener(this);
        mNavigation.setItemIconTintList(null);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void loadFirstItem() {
        getProgressDialog().show();
        updatesToolbarTitle(R.string.item_title);
        new ServiceGetDataFromApi().getDataFromApi(new Request("2", "-23", "-46", "28|", "São Paulo - SP, Brazil"));
    }

    private void updatesToolbarTitle(int title) {
        try {
            getSupportActionBar().setTitle(title);
        } catch (NullPointerException e) {
            showErrorToast();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START))
            mDrawer.closeDrawer(GravityCompat.START);
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(this, R.string.no_permission_granted, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
