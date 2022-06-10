package com.example.foodlab.screens.login.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.login.layout.LoginScreen
import com.example.foodlab.screens.login.viewModel.LoginViewModel

object LoginRoute : NavRoute<LoginViewModel> {
    override val route = Route.LoginScreen.route

    @Composable
    override fun viewModel(): LoginViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: LoginViewModel) =
        LoginScreen(
            viewModel.state,
            viewModel.events
        )


}