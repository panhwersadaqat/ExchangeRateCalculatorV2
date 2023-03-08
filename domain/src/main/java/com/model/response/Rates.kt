package com.model.response


import com.squareup.moshi.JsonClass

/**
response class of rates
 */
@JsonClass(generateAdapter = true)
data class Rates(
    val USD: Double? = 0.0,
    val AED: Double? = 0.0,
    val PKR: Double? = 0.0,
    val IDR: Double? = 0.0,
    val KID: Double? = 0.0,

)