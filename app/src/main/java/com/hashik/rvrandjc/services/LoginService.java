package com.hashik.rvrandjc.services;

import com.hashik.rvrandjc.models.JSONDataModels.JSONData;
import com.hashik.rvrandjc.models.JSONDataModels.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/raw/smkMA2by")
    @Headers("Content-type: application/json")
    Call<JSONData> initiateLogin();
}
