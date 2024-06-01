package com.hnrylvo.inmomarket.data.networking

import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest
import com.hnrylvo.inmomarket.data.networking.model.response.LoginResponse
import com.hnrylvo.inmomarket.data.utils.ApiConstants
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(value = ApiConstants.LOGIN_PATH)
    suspend fun login(@Body api: LoginRequest) : LoginResponse
}