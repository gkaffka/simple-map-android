package com.kaffka.simplemap.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kaffka.simplemap.Adapters.AdapterSimpleMapData;
import com.kaffka.simplemap.Events.EventDataFound;
import com.kaffka.simplemap.Models.Request;
import com.kaffka.simplemap.Models.Unit;
import com.kaffka.simplemap.R;
import com.kaffka.simplemap.Services.ServiceGetDataFromApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivitySimpleList extends ActivityBase {

    @Bind(R.id.toolbar_actionbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecycler;

    private AdapterSimpleMapData mAdapterSimpleMap;
    private List<Unit> mUnitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        initToolbar();
        initRecyclerView();
        getProgressDialog().show();
        new ServiceGetDataFromApi().getDataFromApi(new Request("2", "-23", "-46", "28|", "São Paulo - SP, Brazil"));
    }

    public void onEventMainThread(EventDataFound ev) {
        getProgressDialog().dismiss();
        if (ev.getUnits() == null || ev.getUnits().getUnitList() == null || mUnitList == null) {
            showErrorToast();
            return;
        }
        mUnitList.clear();
        mUnitList.addAll(ev.getUnits().getUnitList());
        mAdapterSimpleMap.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                finish();
                return true;
        }
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        try {
            getSupportActionBar().setTitle(R.string.action_item);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (NullPointerException e) {
            showErrorToast();
        }
    }

    private void initRecyclerView() {
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mUnitList = new ArrayList<>();
        mAdapterSimpleMap = new AdapterSimpleMapData(mUnitList);
        mRecycler.setAdapter(mAdapterSimpleMap);
    }
}
