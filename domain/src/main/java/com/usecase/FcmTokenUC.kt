package com.usecase

import com.repository.AuthRepository
import com.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FcmTokenUC @Inject constructor(
    private val repository: AuthRepository
):UseCase<Boolean,String>(){
    override suspend fun run(params: String): Flow<Boolean> {
        repository.saveFCMToken(params)
        return flow { true }
    }


}