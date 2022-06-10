package com.example.foodlab.screens.order.viewModel

import com.example.api.models.Meal
import com.example.api.models.OrderRequest
import com.example.api.models.Portion
import com.example.api.models.SideDish

data class OrderState(
    var selectedFoodName: String = "",
    var selectedFoodPrice: String = "0",
    var selectedFoodPortions: List<Pair<Portion, Boolean>>? = null,
    var selectedFoodAdditives: List<Pair<SideDish, Boolean>>? = null,
    var selectedPortion: Portion? = null,
    var selectedSideDish: SideDish? = null,


    var options: List<String>? = null,
    var order: String? = null,
    var meal: Meal = Meal(),
    var portion: Portion? = null,
    var listOfPortions: List<Portion>? = null,

    var sideDish: SideDish? = null,
    var sucssesPostOrder: Int = 0,

    var orderRequest: OrderRequest? = null


)