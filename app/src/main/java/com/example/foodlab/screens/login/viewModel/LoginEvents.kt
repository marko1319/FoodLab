package com.example.foodlab.screens.login.viewModel

import com.example.api.models.Employee

sealed class LoginEvents {
    data class FirstNameUpdated(var text: String) : LoginEvents()
    data class LastNameUpdated(var text: String) : LoginEvents()
    object LogIn: LoginEvents()
}