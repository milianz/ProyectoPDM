package com.hnrylvo.inmomarket.data.repository

import com.hnrylvo.inmomarket.data.networking.ApiClient
import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest

class AuthRepository() : BaseRepository() {
    val apiService = ApiClient.apiService

    suspend fun login(request: LoginRequest) = fetchData { apiService.login(request) }
}