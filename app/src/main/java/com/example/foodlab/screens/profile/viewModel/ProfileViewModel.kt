package com.example.foodlab.screens.profile.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlab.core.api.ApiFoodLab
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.core.navigation.RouteNavigator
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.menu.viewModel.MenuEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {
    val state: MutableState<ProfileState> = mutableStateOf(ProfileState())
    val events = ViewModelEvents<ProfileEvents>(viewModelScope)

    init {

        getFavoriteMeal()
        getMe()

        events.listenForEvents { event ->
            when (event) {
                is ProfileEvents.menuButtonClicked -> {
                    navigateBackToMenu()
                }
            }
        }
    }

    private fun navigateBackToMenu() {
        routeNavigator.popToRoute(Route.MenuScreen.route)
    }

    fun getFavoriteMeal() {
        ApiFoodLab
            .instance()
            .getFavoriteMeal()
            .observeOn(Schedulers.newThread())
            .subscribe(
                { resault ->
                    state.value = state.value.copy(
                        listFavoriteMeal = resault
                    )
                    Log.d("getFavoriteMeal", "Success")
                },
                {
                    Log.d("getFavoriteMeal", "Failed")
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