package com.jaya.app.core.domain.useCases


import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.common.IntroStatus
import com.jaya.app.core.common.Resource
import com.jaya.app.core.common.handleFailedResponse
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.OtpRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.core.helpers.Info
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCases @Inject constructor(
    private val loginRepository: LoginRepository,
    private val appStore: AppStore
) {

    fun login() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = loginRepository.login()) {
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (isUser) {
                                appStore.login(userId)
                                if (isMatched) {
                                    emit(Data(type = EmitType.BackendSuccess, value = message))
                                    emit(
                                        Data(
                                            type = EmitType.Navigate,
                                            value = Destination.Dashboard
                                        )
                                    )
                                } else {
                                    emit(Data(type = EmitType.BackendError, value = message))
                                }
                            }
                        }

                        else -> {
                            emit(Data(type = EmitType.BackendError, value = message))
                        }
                    }
                }
            }

            is Resource.Error -> {
                handleFailedResponse(
                    response = response,
                    message = response.message,
                    emitType = EmitType.NetworkError
                )
            }

            else -> {

            }
        }
    }




}