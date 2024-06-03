package com.hnrylvo.inmomarket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hnrylvo.inmomarket.ux.login.LoginRoute
import com.hnrylvo.inmomarket.ux.login.LoginScreen
import com.hnrylvo.inmomarket.ux.register.RegisterRoute
import com.hnrylvo.inmomarket.ux.register.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppScreens.Login.route) {
        composable(LoginRoute.route) {
            LoginScreen(navController)
        }
        composable(RegisterRoute.route) {
            RegisterScreen(navController)
        }
    }
}