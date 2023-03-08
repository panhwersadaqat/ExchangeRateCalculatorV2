package com.model.request

import com.google.gson.annotations.SerializedName

/**
data class of currency data
*/

data class CurrencyRequest (
    @SerializedName("api_key")
    var api_key: String? = "",
    @SerializedName("from")
    var from: String? = "",
    @SerializedName("to")
    var to: String? = "",
    @SerializedName("amount")
    var amount: Double? = 0.0,
)