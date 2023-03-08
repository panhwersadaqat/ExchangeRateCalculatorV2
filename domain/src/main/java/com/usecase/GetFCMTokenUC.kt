package com.domain.usecase.auth

import com.domain.usecase.UseCase
import com.repository.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFCMTokenUC @Inject constructor(private val session: UserSession) : UseCase<String?, UseCase.None?>() {

    override suspend fun run(params: None?): Flow<String?> = flow { emit(session.getFCMToken()) }

}