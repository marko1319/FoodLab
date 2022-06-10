package com.example.foodlab.screens.menu.viewModel

import com.example.foodlab.screens.login.viewModel.LoginEvents
import com.example.foodlab.screens.order.viewModel.OrderEvents

sealed class MenuEvents {
    data class MealClicked(var mealId: String): MenuEvents()

    object profileAvatarClicked: MenuEvents()
    object dontWantToOrderClicked: MenuEvents()

    data class SearchUpdated(var text: String) : MenuEvents()
}