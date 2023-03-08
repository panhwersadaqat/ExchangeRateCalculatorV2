package com.example.presenter

import com.model.response.CurrencyResponse

sealed class CurrencyEvents {
    class GetCurrencyList(val response: CurrencyResponse?): CurrencyEvents()
    class OnFailed(val msg: String): CurrencyEvents()

}
