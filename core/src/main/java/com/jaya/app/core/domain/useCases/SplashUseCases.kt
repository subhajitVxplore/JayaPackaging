package com.jaya.app.core.domain.useCases


import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.common.IntroStatus
import com.jaya.app.core.common.Resource
import com.jaya.app.core.common.handleFailedResponse
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.core.helpers.Info
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashUseCases @Inject constructor(
    private val splashRepository: SplashRepository,
    private val appInfo: Info,
    private val appStore: AppStore
) {


    fun getBaseUrl() = flow{
        when (val response = splashRepository.baseUrl()) {
            is Resource.Success -> {
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.BaseUrl, value = base_url))
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





    fun checkAppVersion() = flow {
        val currentVersion = appInfo.getCurrentVersion()
        when (val response = splashRepository.appVersion(currentVersion)) {
            is Resource.Success -> {
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (currentVersion < appVersion.versionCode.toInt()) {
                                emit(Data(type = EmitType.AppVersion, value = appVersion))
                            } else {
                                    if(appStore.isLoggedIn()) {
                                        emit(Data(type = EmitType.Navigate,value = Destination.Dashboard))
                                    } else {
                                        emit(Data(type = EmitType.Navigate,value = Destination.Login))
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


    fun navigateToAppropiateScreen() = flow {
        if(appStore.isLoggedIn()) {
            emit(Data(type = EmitType.Navigate, value = Destination.Dashboard))
        } else {
            emit(Data(type = EmitType.Navigate, value = Destination.Login))
        }
    }



}