package com.model.request

import com.squareup.moshi.JsonClass

/**
data class of currency data
*/

@JsonClass(generateAdapter = true)
data class CurrencyRequest (
    var api_key: String? = "",
    var from: String? = "",
    var to: String? = "",
    var amount: Double? = 0.0,
)