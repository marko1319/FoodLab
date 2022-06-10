package com.example.foodlab.screens.order.viewModel

import com.example.api.models.OrderRequest
import com.example.api.models.Portion
import com.example.api.models.SideDish

sealed class OrderEvents {
    object Login: OrderEvents()
    object xPressed: OrderEvents()
    data class confirmOrder(val order: OrderRequest): OrderEvents()

    data class onPortionClick(val portionSize: Portion): OrderEvents()
    data class onSideDishClick(val sideDish: SideDish): OrderEvents()
}