package com.example.foodlab.core.navigation

sealed class Route(val route: String) {
    object HomeScreen: Route("home_screen")
    object LoginScreen: Route("login_screen")
    object MenuScreen: Route("menu_screen")
    object OrderScreen: Route("order_screen/{mealId}")
    object ConfirmationScreen: Route("confirmation_screen")
    object ProfileScreen: Route("profile_screen")
    object DontWantToOrderScreen: Route("dontorder_screen")

    fun sendArgument(key: String, arg: String) : String{
        if(route.contains("{") && route.contains("}")){
            return route.replace("{$key}", arg)
        }
        else{
            return route
        }
    }
}