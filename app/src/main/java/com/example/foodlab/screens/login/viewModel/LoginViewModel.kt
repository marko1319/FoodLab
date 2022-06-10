package com.example.foodlab.screens.login.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlab.core.api.ApiFoodLab
import com.example.foodlab.core.api.AppAuth.instance
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.core.navigation.RouteNavigator
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.login.route.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator
{
    val state: MutableState<LoginState> = mutableStateOf(LoginState())
    val events = ViewModelEvents<LoginEvents>(viewModelScope)

    init {
        events.listenForEvents { event ->
            when(event) {
                is LoginEvents.LogIn -> {
                    logInUser()
                }
                is LoginEvents.FirstNameUpdated -> {
                    updateFirstNameText(event.text)
                }
                is LoginEvents.LastNameUpdated -> {
                    updateLastNameText(event.text)
                }
            }
        }
    }

    private fun logInUser() {
        state.value = state.value.copy(
            isLoading = true
        )
        ApiFoodLab
            .instance()
            .logIn(state.value.firstName, state.value.lastName)
            .observeOn(Schedulers.newThread())
            .subscribe(
                { result ->
                    instance().authenticateUser(result)
                    state.value = state.value.copy(
                        isUserLoggedIn = true,
                        isLoading = false
                    )
                    routeNavigator.navigateToRoute(Route.MenuScreen.route)
                    Log.d("logIn", "Success")
                },
                {
                    Log.d("logIn", "Failed")
                    state.value = state.value.copy(
                        isLoading = false
                    )
                }
            )

    }

    fun updateFirstNameText(newText: String){
        state.value = state.value.copy(
            firstName = newText
        )
        validation()
    }

    fun updateLastNameText(newText: String){
        state.value = state.value.copy(
            lastName = newText
        )
        validation()
    }

    fun controlButton(enabled : Boolean){
        state.value = state.value.copy(
            buttonEnabled = enabled
        )
    }

    fun validation() {
        if (state.value.firstName.isNotEmpty() && state.value.lastName.isNotEmpty()) {
            controlButton(enabled = true)
        } else {
            controlButton(enabled = false)
        }
    }
}