package com.example.foodlab.screens.dontWantToOrder.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.dontWantToOrder.layout.DontWantToOrderScreen
import com.example.foodlab.screens.dontWantToOrder.viewModel.DontWantToOrderViewModel

object DontWantToOrderRoute : NavRoute<DontWantToOrderViewModel> {
    override val route = Route.DontWantToOrderScreen.route

    @Composable
    override fun viewModel(): DontWantToOrderViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: DontWantToOrderViewModel) =
        DontWantToOrderScreen(
            viewModel.state,
            viewModel.events
        )


}