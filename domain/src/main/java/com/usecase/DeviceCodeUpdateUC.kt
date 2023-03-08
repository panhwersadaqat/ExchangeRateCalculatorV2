package com.domain.usecase.auth


import com.repository.AuthRepository
import com.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeviceCodeUpdateUC @Inject constructor(private val authRepository: AuthRepository):
UseCase<MessageResponse?, DeviceCodeRequest>(){
    override suspend fun run(params: DeviceCodeRequest): Flow<MessageResponse?> =
        authRepository.deviceTokenUpdate(params)
}