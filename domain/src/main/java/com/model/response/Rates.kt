package com.model.response


import com.squareup.moshi.JsonClass

/**
response class of rates
 */
@JsonClass(generateAdapter = true)
data class Rates(
    val uSD: Double? = 0.0,
    val aED: Double? = 0.0,
    val aRS: Double? = 0.0,
    val aUD: Double? = 0.0,
    val cAD: Double? = 0.0,

)