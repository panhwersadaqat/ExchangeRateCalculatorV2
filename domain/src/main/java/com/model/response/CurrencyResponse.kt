package com.model.response

import com.squareup.moshi.JsonClass

/**
data class of currency data
*/

@JsonClass(generateAdapter = true)
data class CurrencyResponse (
    val amount: String? = "",
    val base_currency_code: String? = "",
    val base_currency_name: String? = "",
    var rates: HashMap<String, Rates> = HashMap(),
    val status: String? = "",
    val updated_date: String? = ""
)