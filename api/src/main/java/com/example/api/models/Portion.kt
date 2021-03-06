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
 * @param id 
 * @param propertySize 
 * @param price 
 */

data class Portion (

    @Json(name = "id")
    val id: java.util.UUID? = null,

    @Json(name = "size")
    val propertySize: kotlin.String? = null,

    @Json(name = "price")
    val price: kotlin.Double? = null

)

