package com.hnrylvo.inmomarket.navigation

sealed class AppScreens(val route: String) {
    object Login : AppScreens("login")
    object Register : AppScreens("register")
}
