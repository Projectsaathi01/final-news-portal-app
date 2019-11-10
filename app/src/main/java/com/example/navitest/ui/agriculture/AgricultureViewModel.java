package com.example.navitest.ui.agriculture;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitest.MainActivity;
import com.example.navitest.NewsDetailActivity;
import com.example.navitest.api.ApiClient;
import com.example.navitest.api.ApiInterface;
import com.example.navitest.models.Agriculture;
import com.example.navitest.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgricultureViewModel extends ViewModel {


    private Adapter adapter;

    private MutableLiveData<List<Agriculture>> agricultures;



    public LiveData<List<Agriculture>> getAgriculture() {
        if(agricultures == null){
            agricultures = new MutableLiveData<List<Agriculture>>();
            LoadJson();
        }

        return agricultures;

    }

    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Agriculture>> call;
        call = apiInterface.getAgriculture();

        call.enqueue(new Callback<List<Agriculture>>() {
            @Override
            public void onResponse(Call<List<Agriculture>> call, Response<List<Agriculture>> response) {
                Log.d("Success", "onResponse");
                agricultures.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Agriculture>> call, Throwable t) {
                Log.d("error", "onError");
            }
        });
    }




}