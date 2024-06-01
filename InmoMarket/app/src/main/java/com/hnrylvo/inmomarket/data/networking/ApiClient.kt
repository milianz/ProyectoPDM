package com.hnrylvo.inmomarket.data.networking

import com.hnrylvo.inmomarket.data.utils.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit : Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apiService : ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}