package com.example.foodlab.screens.home.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.home.layout.HomeScreen
import com.example.foodlab.screens.home.viewModel.HomeViewModel

object HomeRoute : NavRoute<HomeViewModel> {
    override val route = Route.HomeScreen.route

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) =
        HomeScreen(
            viewModel.state,
            viewModel.events
        )


}