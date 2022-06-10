package com.example.foodlab.screens.login.viewModel

data class LoginState(
    var firstName: String = "",
    var lastName: String = "",
    var buttonEnabled: Boolean = false,
    var isUserLoggedIn: Boolean = false,
    var isLoading: Boolean = false
)

