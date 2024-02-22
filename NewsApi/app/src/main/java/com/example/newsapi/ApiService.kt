package com.example.newsapi

import com.example.newsapi.Model.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("top-headlines?apiKey=3cad6689e271421599ceec82b1f65377")
    fun getTopNews(@Query("country") countryCode: String): Call<TopNewsResponse>

    @GET("top-headlines?apiKey=3cad6689e271421599ceec82b1f65377")
    fun getCategoryNews(
        @Query("country") countryCode: String,
        @Query("category") category: String
    ): Call<TopNewsResponse>
}