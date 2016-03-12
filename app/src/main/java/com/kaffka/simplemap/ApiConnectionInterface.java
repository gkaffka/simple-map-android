package com.kaffka.simplemap;

import com.kaffka.simplemap.Models.Request;
import com.kaffka.simplemap.Models.Units;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ApiConnectionInterface {
    String BASE_API_URL = "http://buscasaude.prefeitura.sp.gov.br";

    @POST("/Default.aspx/BuscarTodasUBSs")
    void getDataFromApi(@Body Request body, Callback<Units> cb);

}
