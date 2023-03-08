package com.model.response

import com.squareup.moshi.JsonClass

/**
data class of currency data
*/

@JsonClass(generateAdapter = true)
data class CurrencyResponse (
    val result: String? = "",
    val provider: String? = "",
    val base_code: String? = "",
    val rates: Rates? = null,
)