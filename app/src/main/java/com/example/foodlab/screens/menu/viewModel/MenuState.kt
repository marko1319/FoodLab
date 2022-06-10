package com.example.foodlab.screens.menu.viewModel

import com.example.api.models.Meal

data class MenuState(
    var username: String? = "",
    var listMenu: List<Meal> = mutableListOf(),
    var search: String = ""
)