package com.example.foodlab.screens.menu.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.menu.layout.MenuScreen
import com.example.foodlab.screens.menu.viewModel.MenuViewModel

object MenuRoute : NavRoute<MenuViewModel> {
    override val route = Route.MenuScreen.route

    @Composable
    override fun viewModel(): MenuViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: MenuViewModel) =
        MenuScreen(
            viewModel.state,
            viewModel.events
        )


}