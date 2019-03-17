package com.hashik.rvrandjc.viewmodels;

import android.content.Context;
import android.util.Log;

import com.hashik.rvrandjc.models.JSONDataModels.JSONData;
import com.hashik.rvrandjc.models.JSONDataModels.UserData;
import com.hashik.rvrandjc.services.LoginService;
import com.hashik.rvrandjc.services.ServiceGenerator;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SignInViewModel extends ViewModel {

    public void validateCredentials(String username, String pass){
        final LoginService loginService = ServiceGenerator.createService(LoginService.class);
        Call<JSONData> call = loginService.initiateLogin();

        call.enqueue(new Callback<JSONData>() {
            @Override
            public void onResponse(Call<JSONData> call, Response<JSONData> response) {
                Log.d(TAG, "onResponse: Got the response!"+response.body().getUser().getNumber());
            }

            @Override
            public void onFailure(Call<JSONData> call, Throwable t) {

            }
        });
    }
}
