package com.example.foodlab.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.foodlab.screens.confirmation.route.ConfirmationRoute
import com.example.foodlab.screens.dontWantToOrder.route.DontWantToOrderRoute
import com.example.foodlab.screens.home.route.HomeRoute
import com.example.foodlab.screens.login.route.LoginRoute
import com.example.foodlab.screens.menu.route.MenuRoute
import com.example.foodlab.screens.order.route.OrderRoute
import com.example.foodlab.screens.profile.route.ProfileRoute

@Composable
fun FoodLabNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.route,
        builder = {
            HomeRoute.composable(this,navController)
            LoginRoute.composable(this,navController)
            MenuRoute.composable(this,navController)
            OrderRoute.composable(this,navController)
            ConfirmationRoute.composable(this,navController)
            ProfileRoute.composable(this,navController)
            DontWantToOrderRoute.composable(this, navController)
        }
    )
}