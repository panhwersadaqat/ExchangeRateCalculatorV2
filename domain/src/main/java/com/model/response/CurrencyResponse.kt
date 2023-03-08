package com.model.response

import com.google.gson.annotations.SerializedName

/**
data class of currency data
*/

data class CurrencyResponse (
    @SerializedName("amount")
    val amount: String? = "",
    @SerializedName("base_currency_code")
    val base_currency_code: String? = "",
    @SerializedName("base_currency_name")
    val base_currency_name: String? = "",
    @SerializedName("rates")
    var rates: HashMap<String, Rates> = HashMap(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("updated_date")
    val updated_date: String? = ""
)