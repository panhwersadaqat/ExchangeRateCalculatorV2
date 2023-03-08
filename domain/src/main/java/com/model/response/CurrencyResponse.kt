package com.model.response

import com.squareup.moshi.JsonClass

/**
data class of currency data
*/

@JsonClass(generateAdapter = true)
data class CurrencyResponse (
    val base: String? = "",
    val result: String? = "",
    val provider: String? = "",
    val documentation: String? = "",
    val terms_of_use: String? = "",
    val base_code: String? = "",
    val rates: Rates?=null,
    val success: Boolean? = false,
)