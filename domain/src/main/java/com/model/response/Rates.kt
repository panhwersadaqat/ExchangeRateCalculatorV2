package com.model.response

import com.google.gson.annotations.SerializedName


data class Rates(
    @SerializedName("currency_name")
    val currency_name: String? = "",
    @SerializedName("rate")
    val rate: String? = "",
    @SerializedName("rate_for_amount")
    val rate_for_amount: Double? = 0.0
)
