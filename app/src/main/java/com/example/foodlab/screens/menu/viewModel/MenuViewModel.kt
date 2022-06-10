package com.example.foodlab.screens.menu.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlab.core.api.ApiFoodLab
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.core.navigation.RouteNavigator
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.login.viewModel.LoginEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class MenuViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {
    val state: MutableState<MenuState> = mutableStateOf(MenuState())
    val events = ViewModelEvents<MenuEvents>(viewModelScope)

    init {
        getMenu(OffsetDateTime.now())
        getMe()

        events.listenForEvents { event ->
            when(event) {
                is MenuEvents.MealClicked -> {
                    nvaigateToOrder(event.mealId)
                }
                is MenuEvents.profileAvatarClicked -> {
                    navigateToProfile()
                }
                is MenuEvents.dontWantToOrderClicked -> {
                    navigateToDontWantToOrder()
                }
                is MenuEvents.SearchUpdated ->{
                    updateSearchText(event.text)
                }
            }
        }
    }

    private fun updateSearchText(text: String) {
        state.value = state.value.copy(
            search = text
        )
    }

    private fun navigateToDontWantToOrder() {
        routeNavigator.navigateToRoute(Route.DontWantToOrderScreen.route)
    }

    private fun navigateToProfile() {
        routeNavigator.navigateToRoute(Route.ProfileScreen.route)
    }

    private fun nvaigateToOrder(mealId: String) {
        navigateToRoute(Route.OrderScreen.sendArgument("mealId", mealId))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMenu(dateTime: OffsetDateTime) {

        val date: OffsetDateTime = dateTime.minusDays(1)

        ApiFoodLab
            .instance()
            .getMenu(date)
            .observeOn(Schedulers.newThread())
            .subscribe(
                { result ->
                    state.value = state.value.copy(
                        listMenu = result,

                        )

                    Log.d("getMenu", "Success")
                },
                {

                    Log.d("getMenu", "Failed")
                }
            )

    }

    fun getMe() {
        ApiFoodLab
            .instance()
            .getMe()
            //.observeOn(Schedulers.newThread())
            .subscribe(
                { resault ->
                    state.value = state.value.copy(
                        username = resault.firstName
                    )
                    Log.d("getMe", "Success")
                },
                {
                    Log.d("getMe", "Failed")
                }
            )
    }

}

