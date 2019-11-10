package com.example.navitest.ui.home;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitest.api.ApiClient;
import com.example.navitest.api.ApiInterface;
import com.example.navitest.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articles;
//    private MutableLiveData<List<Movie>> movies;


    public LiveData<List<Article>> getArticles() {
        if(articles == null){
            articles = new MutableLiveData<List<Article>>();
            LoadJson();
        }
//        if(movies == null){
//            movies = new MutableLiveData<List<Movie>>();
//            loadMovies();
//        }

        return articles;

    }




    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Article>> call;
        call = apiInterface.getArticles();

        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                Log.d("Success", "onResponse");
                articles.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.d("error", "onError");
            }
        });
    }
//
//    public void loadMovies() {
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<Movie>> call;
//        call = apiInterface.getMovies();
//
//        call.enqueue(new Callback<List<Movie>>() {
//            @Override
//            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
//                movies.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Movie>> call, Throwable t) {
//
//            }
//        });
//    }
}