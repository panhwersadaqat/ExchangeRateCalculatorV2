package com.example.data.repository

import com.model.request.CurrencyRequest
import com.model.response.CurrencyResponse
import com.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRemoteSourceImp @Inject constructor(
    private val roomRemoteSource: CurrencyRemoteSource
    ): CurrencyRepository {
    override suspend fun currencyListResponse(currencyRequest: CurrencyRequest?): Flow<CurrencyResponse?> {
        return roomRemoteSource.currencyListResponse(currencyRequest)
    }

}