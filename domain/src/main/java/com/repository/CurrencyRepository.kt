package com.repository

import com.model.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow


interface CurrencyRepository {
    suspend fun currencyListResponse() : Flow<CurrencyResponse?>
}
