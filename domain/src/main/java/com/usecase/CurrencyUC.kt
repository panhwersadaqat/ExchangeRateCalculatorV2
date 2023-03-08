package com.usecase

import com.domain.usecase.UseCase
import com.model.response.CurrencyResponse
import com.repository.CurrencyRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class CurrencyUC @Inject constructor(
    private val currencyRepository: CurrencyRepository)
    :UseCase<CurrencyResponse?,UseCase.None?>() {
    override suspend fun run(params: None?): Flow<CurrencyResponse?> =
        currencyRepository.currencyListResponse()
}

