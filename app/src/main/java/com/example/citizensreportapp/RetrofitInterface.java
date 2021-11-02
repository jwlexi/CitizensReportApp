package com.example.citizensreportapp;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<Login> executeLogin(@Body HashMap<String, String> map);
}
