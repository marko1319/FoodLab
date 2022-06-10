package com.example.foodlab.screens.profile.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodlab.core.navigation.NavRoute
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.screens.profile.layout.ProfileScreen
import com.example.foodlab.screens.profile.viewModel.ProfileViewModel

object ProfileRoute : NavRoute<ProfileViewModel> {
    override val route = Route.ProfileScreen.route

    @Composable
    override fun viewModel(): ProfileViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ProfileViewModel) =
        ProfileScreen(
            viewModel.state,
            viewModel.events
        )


}