package com.example.foodlab.screens.home.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.core.navigation.RouteNavigator
import com.example.foodlab.core.utils.ViewModelEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator
{
    val state: MutableState<HomeState> = mutableStateOf(HomeState())
    val events = ViewModelEvents<HomeEvents>(viewModelScope)

    init {

        events.listenForEvents { event ->
            when(event) {
                is HomeEvents.GoToLogin -> {
                    goToLogin()
                }
            }
        }
    }

    private fun goToLogin() {
        routeNavigator.navigateToRoute(Route.LoginScreen.route)
    }
}