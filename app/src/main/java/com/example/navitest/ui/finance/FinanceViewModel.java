package com.example.navitest.ui.finance;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitest.api.ApiClient;
import com.example.navitest.api.ApiInterface;
import com.example.navitest.models.Finance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinanceViewModel extends ViewModel {

    private MutableLiveData<List<Finance>> finances;



    public LiveData<List<Finance>> getFinances() {
        if(finances == null){
            finances = new MutableLiveData<List<Finance>>();
            LoadJson();
        }

        return finances;

    }

    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Finance>> call;
        call = apiInterface.getFinances();

        call.enqueue(new Callback<List<Finance>>() {
            @Override
            public void onResponse(Call<List<Finance>> call, Response<List<Finance>> response) {
                Log.d("Success", "onResponse");
                finances.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Finance>> call, Throwable t) {
                Log.d("error", "onError");
            }
        });
    }
}