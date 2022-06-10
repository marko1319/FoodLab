package com.example.foodlab.screens.profile.viewModel

import com.example.api.models.FavoriteMeal
import com.example.api.models.Meal

data class ProfileState(
    var profile: String? = null,
    var username: String? = "",
    var listFavoriteMeal: List<FavoriteMeal> = mutableListOf()
)