package com.example.deezermusicapp.Network

import com.example.deezermusicapp.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        private var retrofit: Retrofit? = null

        fun init(): ApiService {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }

        fun getHeaders(): MutableMap<String, String> {
            return mutableMapOf(
                "X-RapidAPI-Key" to "1233786735msh420929d4bbccc88p1ca725jsn34576d8212a9",
                "X-RapidAPI-Host" to "deezerdevs-deezer.p.rapidapi.com"
            )
        }
    }
}