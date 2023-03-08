package com.usecase


import com.domain.usecase.UseCase
import com.model.request.AuthRequest
import com.model.response.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/*
class AuthCodeUC @Inject constructor(private val authRepository: AuthRepository) : UseCase<AuthResponse?, AuthRequest>() {
    override suspend fun run(params: AuthRequest): Flow<AuthResponse?> =
        authRepository.authRequest(params).map {
            authRepository.saveToken(it?.access_token ?: "")
            it
        }

}*/
