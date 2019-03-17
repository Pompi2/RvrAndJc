package com.hashik.rvrandjc.services;

import com.hashik.rvrandjc.models.JSONDataModels.JSONData;
import com.hashik.rvrandjc.models.JSONDataModels.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface LoginService {
    @GET("/posts/2")
    @Headers("Content-type: application/json")
    Call<JSONData> initiateLogin();
}
