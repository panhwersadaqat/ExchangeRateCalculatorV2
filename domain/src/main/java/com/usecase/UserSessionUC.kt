package com.domain.usecase.auth

import com.domain.usecase.UseCase
import com.repository.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserSessionUC @Inject constructor(private val session: UserSession) : UseCase<Boolean, UseCase.None?>() {

    override suspend fun run(params: None?): Flow<Boolean> = flow { emit(session.isUserLoggedIn()) }

}