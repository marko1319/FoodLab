package com.example.foodlab.screens.dontWantToOrder.viewModel

import com.example.foodlab.screens.confirmation.viewModel.ConfirmationEvents

sealed class DontWantToOrderEvents {
    object Confirm: DontWantToOrderEvents()

}