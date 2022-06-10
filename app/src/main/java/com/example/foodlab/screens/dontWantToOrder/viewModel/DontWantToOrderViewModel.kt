package com.example.foodlab.screens.dontWantToOrder.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.core.navigation.RouteNavigator
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.dontWantToOrder.viewModel.DontWantToOrderEvents
import com.example.foodlab.screens.dontWantToOrder.viewModel.DontWantToOrderState
import com.example.foodlab.screens.menu.viewModel.MenuEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DontWantToOrderViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator
{
    val state: MutableState<DontWantToOrderState> = mutableStateOf(DontWantToOrderState())
    val events = ViewModelEvents<DontWantToOrderEvents>(viewModelScope)

    init {
        events.listenForEvents { event ->
            when(event) {
                is DontWantToOrderEvents -> {
                    navigateBackToMenu()
                }
            }
        }
    }

    private fun navigateBackToMenu() {
        routeNavigator.popToRoute(Route.MenuScreen.route)
    }


}