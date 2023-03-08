package com.usecase

import com.domain.usecase.UseCase
import com.model.request.CurrencyRequest
import com.model.response.CurrencyResponse
import com.repository.CurrencyRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class CurrencyUC @Inject constructor(
    private val currencyRepository: CurrencyRepository)
    :UseCase<CurrencyResponse?,CurrencyRequest?>() {
    override suspend fun run(params: CurrencyRequest?): Flow<CurrencyResponse?> =
        currencyRepository.currencyListResponse(params)
}

