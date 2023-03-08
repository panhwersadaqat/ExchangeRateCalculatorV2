package com.repository

import com.model.request.CurrencyRequest
import com.model.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow


interface CurrencyRepository {
    suspend fun currencyListResponse(currencyRequest: CurrencyRequest?) : Flow<CurrencyResponse?>
}
