package com.hashik.rvrandjc.viewmodels;

import android.content.Context;
import android.util.Log;

import com.hashik.rvrandjc.models.GlobalApplication;
import com.hashik.rvrandjc.models.JSONDataModels.JSONData;
import com.hashik.rvrandjc.models.JSONDataModels.UserData;
import com.hashik.rvrandjc.services.LoginService;
import com.hashik.rvrandjc.services.ServiceGenerator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SignInViewModel extends ViewModel {
    private MutableLiveData<Boolean> validCreds;
    private MutableLiveData<Boolean> processing;

    public void validateCredentials(String username, String pass){

        final LoginService loginService = ServiceGenerator.createService(LoginService.class);
        Call<JSONData> call = loginService.initiateLogin();
        processing.setValue(true);
        call.enqueue(new Callback<JSONData>() {
            @Override
            public void onResponse(Call<JSONData> call, Response<JSONData> response) {
                Log.d(TAG, "onResponse: Got the response!"+response.body().getUser().getNumber());
                GlobalApplication.setUserData(response.body());
                validCreds.setValue(true);
                processing.setValue(false);
            }

            @Override
            public void onFailure(Call<JSONData> call, Throwable t) {
                processing.setValue(false);
                validCreds.setValue(false);
            }
        });
    }

    public LiveData<Boolean> getValidCreds() {
        if(validCreds == null)
            validCreds = new MutableLiveData<>();
        return validCreds;
    }

    public LiveData<Boolean> getProcessing() {
        if(processing == null)
            processing = new MutableLiveData<>();
        return processing;
    }
    public void setValidCreds(){
        validCreds.setValue(null);
    }
    public void setProcessing(){
        validCreds.setValue(null);
    }

}
