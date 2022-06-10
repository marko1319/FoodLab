package com.example.foodlab.core.api

import com.example.api.infrastructure.ApiClient
import com.example.api.models.Employee

object AppAuth {
    private var instanceField : AppAuth? = null
    fun instance() : AppAuth{
        if (instanceField == null){
            instanceField = AppAuth
        }
        return instanceField!!
    }

    var user: Employee? = null

    fun authenticateUser(user: Employee){
        this.user = user
        ApiClient.accessToken = user.token
    }

}