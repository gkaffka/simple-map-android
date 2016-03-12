package com.kaffka.simplemap.Services;

import com.kaffka.simplemap.Events.EventDataFound;
import com.kaffka.simplemap.Events.EventSomethingIsNotRight;
import com.kaffka.simplemap.Models.Request;
import com.kaffka.simplemap.Models.Units;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ServiceGetDataFromApi extends ServiceBaseService {

    public ServiceGetDataFromApi() {
        super();
    }

    public void getDataFromApi(Request request) {
        getServiceInterface().getDataFromApi(request, new Callback<Units>() {
            @Override
            public void success(Units units, Response response) {
                EventBus.getDefault().post(new EventDataFound(units));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new EventSomethingIsNotRight());
            }
        });
    }
}
