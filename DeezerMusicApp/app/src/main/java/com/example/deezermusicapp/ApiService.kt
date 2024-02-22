package com.example.deezermusicapp

import com.example.deezermusicapp.Model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query


interface ApiService {

    @GET("search")
    fun getSong(@HeaderMap headers: MutableMap<String, String>,@Query("q") artist:String)
    : Call<SearchResponse>
}