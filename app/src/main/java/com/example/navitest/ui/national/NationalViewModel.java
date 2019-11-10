package com.example.navitest.ui.national;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitest.api.ApiClient;
import com.example.navitest.api.ApiInterface;
import com.example.navitest.models.National;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NationalViewModel extends ViewModel {


    private MutableLiveData<List<National>> nationals;

    public NationalViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

    public LiveData<List<National>> getNationalArticles() {
        if(nationals == null){
            nationals = new MutableLiveData<List<National>>();

            LoadJson();

        }

        return nationals;

    }




    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<National>> call;
        call = apiInterface.getNational();

        call.enqueue(new Callback<List<National>>() {
            @Override
            public void onResponse(Call<List<National>> call, Response<List<National>> response) {
                Log.d("Success", "onResponse");


                nationals.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<National>> call, Throwable t) {
                Log.d("error", "onError");
            }
        });

    }
}