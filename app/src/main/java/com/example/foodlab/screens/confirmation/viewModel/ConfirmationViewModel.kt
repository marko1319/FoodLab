package com.example.foodlab.screens.confirmation.viewModel

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
class ConfirmationViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator
{
    val state: MutableState<ConfirmationState> = mutableStateOf(ConfirmationState())
    val events = ViewModelEvents<ConfirmationEvents>(viewModelScope)

    init {
        events.listenForEvents { event ->
            when(event) {
                is ConfirmationEvents.Confirm -> {
                    navigateBackToMenu()
                }
            }
        }
    }

    private fun navigateBackToMenu() {
        routeNavigator.popToRoute(Route.MenuScreen.route)
    }
}