package com.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rates(
    val currency_name: String? = "",
    val rate: String? = "",
    val rate_for_amount: Double? = 0.0
)
