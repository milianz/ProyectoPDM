package com.hnrylvo.inmomarket.data.networking.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName(value = "email")
    val email : String,
    @SerializedName(value = "password")
    val password : String
)
