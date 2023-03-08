package com.example.data.repository

import com.example.data.network.API
import com.example.data.network.NetworkCall
import com.model.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRemoteSource@Inject constructor(
    private val networkCall: NetworkCall
) {

    suspend fun currencyListResponse(): Flow<CurrencyResponse?> {
        return networkCall.get(API.RATELIST,null)
    }
}