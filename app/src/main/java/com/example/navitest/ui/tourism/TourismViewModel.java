package com.example.navitest.ui.tourism;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitest.api.ApiClient;
import com.example.navitest.api.ApiInterface;
import com.example.navitest.models.Tourism;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourismViewModel extends ViewModel {


    private MutableLiveData<List<Tourism>> tourisms;

    public TourismViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

    public LiveData<List<Tourism>> getTourism() {
        if(tourisms == null){
            tourisms = new MutableLiveData<List<Tourism>>();

            LoadJson();

        }

        return tourisms;

    }




    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Tourism>> call;
        call = apiInterface.getTourism();

        call.enqueue(new Callback<List<Tourism>>() {
            @Override
            public void onResponse(Call<List<Tourism>> call, Response<List<Tourism>> response) {
                Log.d("Success", "onResponse");


                tourisms.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Tourism>> call, Throwable t) {
                Log.d("error", "onError");
            }
        });

    }
}