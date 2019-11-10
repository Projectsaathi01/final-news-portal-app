package com.example.navitest.api;

import com.example.navitest.models.Agriculture;
import com.example.navitest.models.Article;
import com.example.navitest.models.Finance;
import com.example.navitest.models.International;
import com.example.navitest.models.National;
import com.example.navitest.models.Tourism;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/national")
    Call<List<Article>> getArticles();


    @GET("api/national")
    Call<List<National>> getNational();

    @GET("api/international")
    Call<List<International>> getInternational();

    @GET("api/finance")
    Call<List<Finance>> getFinances();

    @GET("api/tourism")
    Call<List<Tourism>> getTourism();


    @GET("api/agriculture")
    Call<List<Agriculture>> getAgriculture();



//    @GET("marvel")
//    Call<List<Movie>> getMovies();

}
