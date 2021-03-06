/**
 * SoftLab.rs.FoodLab.API
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.example.api.models


import com.squareup.moshi.Json

/**
 * 
 *
 * @param mealId 
 * @param sideDishId 
 * @param portionId 
 */

data class OrderRequest (

    @Json(name = "mealId")
    var mealId: java.util.UUID? = null,

    @Json(name = "sideDishId")
    var sideDishId: java.util.UUID? = null,

    @Json(name = "portionId")
    var portionId: java.util.UUID? = null

)

