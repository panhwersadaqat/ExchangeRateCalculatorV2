package com.example.data.repository

import com.example.data.network.API
import com.example.data.network.NetworkCall
import com.model.request.CurrencyRequest
import com.model.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRemoteSource@Inject constructor(
    private val networkCall: NetworkCall
) {

   /* suspend fun currencyListResponse(): Flow<CurrencyResponse?> {

        return networkCall.get(API.RATELIST,null)
    }
*/
    suspend fun currencyListResponse(currencyRequest: CurrencyRequest?): Flow<CurrencyResponse?> {
        val query = HashMap<String, String>()
        query["api_key"] = "${currencyRequest?.api_key}"
        query["from"] = "${currencyRequest?.from}"
        query["to"] = "${currencyRequest?.to}"
        query["amount"] = "${currencyRequest?.amount}"
        return networkCall.get(API.RATELIST,query)
    }
}