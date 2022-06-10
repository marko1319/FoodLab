package com.example.foodlab.screens.order.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.order.layout.OrderScreen
import com.example.foodlab.screens.order.viewModel.OrderViewModel

object OrderRoute : NavRoute<OrderViewModel> {
    override val route = Route.OrderScreen.route

    @Composable
    override fun viewModel(): OrderViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: OrderViewModel) =
        OrderScreen(
            viewModel.state,
            viewModel.events
        )


}