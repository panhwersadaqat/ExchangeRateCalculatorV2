package com.domain.usecase.auth

import com.repository.AuthRepository
import javax.inject.Inject

class ClearDataStoreUC @Inject constructor(val authRepository: AuthRepository){

    suspend operator fun invoke() {
        authRepository.logout()
    }
}