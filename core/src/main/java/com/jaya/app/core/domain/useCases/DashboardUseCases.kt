package com.jaya.app.core.domain.useCases

import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.common.Resource
import com.jaya.app.core.common.handleFailedResponse
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.helpers.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardUseCases @Inject constructor(
    private val appStore: AppStore,
    private val dashboardRepository: DashboardRepository
) {

    fun logOutFromDashboard() = flow {
        appStore.logout()
        emit(Data(type = EmitType.Navigate, Destination.Login))
    }

    fun getUserDetails() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = dashboardRepository.getUserDetails()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.USER_DATA, value = user_data))
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

    fun getPackagingList() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = dashboardRepository.getPackagingList()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.PACKAGING_SHIFT, value = shift))
                            emit(Data(type = EmitType.PACKAGING_PLANT, value = plant))
                            emit(Data(type = EmitType.PACKAGING_LIST, value = packaging_list))
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