package com.example.foodlab.screens.order.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.Meal
import com.example.api.models.OrderRequest
import com.example.api.models.Portion
import com.example.api.models.SideDish
import com.example.foodlab.core.api.ApiFoodLab
import com.example.foodlab.core.navigation.Route
import com.example.foodlab.core.navigation.RouteNavigator
import com.example.foodlab.core.utils.ViewModelEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.OffsetDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OrderViewModel
@Inject
constructor(
    private val routeNavigator: RouteNavigator,
    private val saveState: SavedStateHandle
): ViewModel(), RouteNavigator by routeNavigator
{

    val state: MutableState<OrderState> = mutableStateOf(OrderState())
    val events = ViewModelEvents<OrderEvents>(viewModelScope)

    init {

        val mealId = saveState.get<String>("mealId")
        getMealDetails(UUID.fromString(mealId), OffsetDateTime.now())


        events.listenForEvents { event ->
            when(event) {
                is OrderEvents.xPressed -> {
                    navigateToMenu()
                }
                is OrderEvents.confirmOrder -> {
                    postMealOrder(event.order)
                    navigateToConfirmation()
                }
                is OrderEvents.onPortionClick ->{
                    onPortionClick(event.portionSize)
                    //pickPortionSize(event.portionSize)
                }
                is OrderEvents.onSideDishClick ->{
                    onSideDishClick(event.sideDish)

                    //pickSideDish(event.sideDish)
                }

            }
        }


      //  getMealById(OffsetDateTime.now(), UUID.fromString(mealId))
    }

//    private fun pickSideDish(sideDish: SideDish) {
//
//        state.value = state.value.copy(
//            sideDish = sideDish
//        )
//        orderRequest.mealId = state.value.meal.id
//        orderRequest.sideDishId = state.value.sideDish?.id
//        orderRequest.portionId = state.value.portion?.id
//
//        state.value = state.value.copy(
//            orderRequest = orderRequest
//        )
//    }

    private fun postMealOrder(order: OrderRequest) {

        postOrder(order)
    }

//    private fun pickPortionSize(portion: Portion) {
//        state.value = state.value.copy(portion = portion)
//
//        orderRequest.mealId = state.value.meal.id
//        orderRequest.sideDishId = state.value.sideDish?.id
//        orderRequest.portionId = state.value.portion?.id
//
//        state.value = state.value.copy(
//            orderRequest = orderRequest
//        )
//    }




    fun getMealDetails(mealId: UUID,dateTime: OffsetDateTime) {

        val date: OffsetDateTime = dateTime.minusDays(1)

        ApiFoodLab
            .instance()
            .getMealDetails(mealId, date)
            .observeOn(Schedulers.newThread())
            .subscribe(
                { result ->

                    state.value = state.value.copy(
                        meal = result,
                        selectedFoodName = result.name?:"No name",
                        selectedFoodPrice = setStartPrice(result),
                        selectedFoodPortions = getPortions(result.portions),
                        selectedFoodAdditives = getSideDishes(result.sideDishes)
                        )
                    state.value.listOfPortions = state.value.meal.portions
                    Log.d("getMealDetails", "Success")
                },
                {
                    Log.d("getMealDetails", "Failed")
                }
            )
    }

    private fun getPortions(list: List<Portion>?): List<Pair<Portion, Boolean>>{
        var temp : MutableList<Pair<Portion, Boolean>> = mutableListOf()

        if(list != null){
            for ((i, portion: Portion) in list.withIndex()){
                if(i == 0){
                    temp.add(Pair(portion, true))
                }
                temp.add(Pair(portion, false))
            }
        }
        return temp
    }
    private fun getSideDishes(list: List<SideDish>?): List<Pair<SideDish, Boolean>>{
        var temp : MutableList<Pair<SideDish, Boolean>> = mutableListOf()

        if(list != null){
            for ((i, portion: SideDish) in list.withIndex()){
                if(i == 0){
                    temp.add(Pair(portion, true))
                }
                temp.add(Pair(portion, false))
            }
        }
        return temp
    }
    private fun onPortionClick(item: Portion){

        var temp : MutableList<Pair<Portion, Boolean>> = mutableListOf()

        if(state.value.selectedFoodPortions != null){
            temp.forEach { element ->
                if(element.first.id!! == item.id){
                    temp.add(Pair(item, true))
                }
                temp.add(Pair(item, false))
            }
        }

        state.value = state.value.copy(
            selectedPortion = item,
            selectedFoodPortions = temp
        )

    }

    private fun onSideDishClick(item: SideDish){

        var temp : MutableList<Pair<SideDish, Boolean>> = mutableListOf()

        if(state.value.selectedFoodPortions != null){
            temp.forEach { element ->
                if(element.first.id!! == item.id){
                    temp.add(Pair(item, true))
                }
                temp.add(Pair(item, false))
            }
        }

        state.value = state.value.copy(
            selectedSideDish = item,
            selectedFoodAdditives = temp
        )

    }

    private fun setStartPrice(meal: Meal): String {
        if(meal.portions != null && meal.portions!!.isNotEmpty() ){
            return meal.portions!![0].price.toString()
        }
        else return ""

    }

    private fun postOrder(orderRequest: OrderRequest) {
        /*state.value = state.value.copy(
            isLoading = true
        )*/
        ApiFoodLab
            .instance()
            .postOrder(orderRequest)
            .observeOn(Schedulers.newThread())
            .subscribe(
                { result ->
                    state.value = state.value.copy(
                        sucssesPostOrder = 1
                    )

                    /*state.value = state.value.copy(
                       // isLoading = false
                    )*/
                    routeNavigator.navigateToRoute(Route.MenuScreen.route)
                    Log.d("logIn", "Success")
                },
                {
                    Log.d("logIn", "Failed")
                    /*state.value = state.value.copy(
                        //isLoading = false
                    )*/
                }
            )

    }
    private fun navigateToConfirmation() {
        routeNavigator.navigateToRoute(Route.ConfirmationScreen.route)
    }
    private fun navigateToMenu() {
        routeNavigator.popToRoute(Route.MenuScreen.route)
    }
}