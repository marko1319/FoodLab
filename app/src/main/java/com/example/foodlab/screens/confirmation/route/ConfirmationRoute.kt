package com.example.foodlab.screens.confirmation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.confirmation.layout.ConfirmationScreen
import com.example.foodlab.screens.confirmation.viewModel.ConfirmationViewModel

object ConfirmationRoute : NavRoute<ConfirmationViewModel> {
    override val route = Route.ConfirmationScreen.route

    @Composable
    override fun viewModel(): ConfirmationViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ConfirmationViewModel) =
        ConfirmationScreen(
            viewModel.state,
            viewModel.events
        )


}