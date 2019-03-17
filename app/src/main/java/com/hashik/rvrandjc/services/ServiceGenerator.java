package com.hashik.rvrandjc.services;

import android.content.Context;

import com.hashik.rvrandjc.BuildConfig;
import com.hashik.rvrandjc.models.GlobalApplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A service generator for retrofit.
 */
public class ServiceGenerator {

    public static <S> S createService(
            Class<S> serviceClass, Context context) {

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(GlobalApplication.getBaseurl())
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        HttpLoggingInterceptor logging =
                new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            httpClient.addInterceptor(new ConnectivityInterceptor(context));
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }
}