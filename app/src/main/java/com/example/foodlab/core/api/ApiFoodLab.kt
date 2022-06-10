package com.example.foodlab.core.api

import android.util.Log
import com.example.api.apis.FoodLabApi
import com.example.api.infrastructure.ApiClient
import com.example.api.models.*
import io.reactivex.rxjava3.core.Single
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

object ApiFoodLab {


    private var instanceField : ApiFoodLab? = null

    fun instance(authToken : String = "") : ApiFoodLab{
        if (instanceField == null){
            instanceField = ApiFoodLab
        }
        if(authToken.isNotEmpty()){
            ApiClient.accessToken = authToken
        }
        return instanceField!!
    }

    fun logIn(firstName : String , lastName : String): Single<Employee> {

        return Single.create { emitter ->
            Thread {
                try {
                    val response: Employee = FoodLabApi().foodlabLoginPost(firstName, lastName)
                    emitter.onSuccess(response)
                }catch (e: Exception){
                    Log.d("logIn",e.message.toString())
                    emitter.onError(e)
                }
            }.start()
        }
    }

    fun postOrder(orderRequest: OrderRequest): Single<OrderRequest> {

        return Single.create { emitter ->
            Thread {
                try {
                    val response: OrderRequest = FoodLabApi().foodlabOrderPost(orderRequest)
                    emitter.onSuccess(response)
                }catch (e: Exception){
                    Log.d("logIn",e.message.toString())
                    emitter.onError(e)
                }
            }.start()
        }
    }

    fun getMenu(date: OffsetDateTime): Single<List<Meal>>{

        return Single.create { emitter ->
            Thread {
                try {
                    val temp = date.withOffsetSameInstant(ZoneOffset.UTC)
                    val response: List<Meal> = FoodLabApi().foodlabMenuDateGet(temp)

                    emitter.onSuccess(response)
                }catch (e: Exception){
                    Log.d("GetMenu",e.message.toString())
                    emitter.onError(e)
                }
            }.start()
        }
    }

    fun getMe() : Single<Employee>{
        return Single.create { emitter ->
            Thread {
                try {
                    val response: Employee = FoodLabApi().foodlabMeGet()
                    emitter.onSuccess(response)
                }catch (e: Exception){
                    Log.d("getMe",e.message.toString())
                    emitter.onError(e)
                }
            }.start()
        }
    }

    fun getMealDetails(mealId: UUID, date: OffsetDateTime): Single<Meal>{

        return Single.create { emitter ->
            Thread {
                try {
                    val temp = date.withOffsetSameInstant(ZoneOffset.UTC)
                    val response: Meal = FoodLabApi().foodlabMenuDateMealIdGet(mealId, temp)
                    emitter.onSuccess(response)
                }catch (e: Exception){
                    Log.d("getMealDetails",e.message.toString())
                    emitter.onError(e)
                }
            }.start()
        }
    }

    fun getFavoriteMeal() : Single<List<FavoriteMeal>>{
        return Single.create { emitter ->
            Thread {
                try {
                    val response: List<FavoriteMeal> = FoodLabApi().foodlabFavoriteMealMeGet()
                    emitter.onSuccess(response)
                }catch (e: Exception){
                    Log.d("getFavoriteMeal",e.message.toString())
                    emitter.onError(e)
                }
            }.start()
        }
    }

}