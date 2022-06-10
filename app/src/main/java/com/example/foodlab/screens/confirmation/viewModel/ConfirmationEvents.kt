package com.example.foodlab.screens.confirmation.viewModel

import com.example.foodlab.screens.order.viewModel.OrderEvents

sealed class ConfirmationEvents {
    object Confirm: ConfirmationEvents()

}