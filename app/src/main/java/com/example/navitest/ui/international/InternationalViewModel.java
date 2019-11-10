package com.example.navitest.ui.international;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitest.api.ApiClient;
import com.example.navitest.api.ApiInterface;
import com.example.navitest.models.International;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InternationalViewModel extends ViewModel {

    private MutableLiveData<List<International>> internationals;



    public LiveData<List<International>> getInternational() {
        if(internationals == null){
            internationals = new MutableLiveData<List<International>>();
            LoadJson();
        }

        return internationals;

    }

    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<International>> call;
        call = apiInterface.getInternational();

        call.enqueue(new Callback<List<International>>() {
            @Override
            public void onResponse(Call<List<International>> call, Response<List<International>> response) {
                Log.d("Success", "onResponse");
                internationals.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<International>> call, Throwable t) {
                Log.d("error", "onError");
            }
        });
    }

}