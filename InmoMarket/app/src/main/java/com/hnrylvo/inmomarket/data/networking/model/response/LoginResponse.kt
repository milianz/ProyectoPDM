package com.hnrylvo.inmomarket.data.networking.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id : String,
    @SerializedName("email")
    val email : String
)

data class ApiResponseError(
    @SerializedName("message")
    val message : String,

    @SerializedName("error")
    val error : String
)
