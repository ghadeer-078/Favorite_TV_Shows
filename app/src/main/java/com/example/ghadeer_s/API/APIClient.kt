package com.example.ghadeer_s.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}