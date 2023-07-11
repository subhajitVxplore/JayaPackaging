package com.jaya.app.core.domain.useCases

import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.common.Resource
import com.jaya.app.core.common.handleFailedResponse
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.domain.repositories.FinalReportRepository
import com.jaya.app.core.helpers.AppStore
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class FinalReportUseCases @Inject constructor(
    private val appStore: AppStore,
    private val finalReportRepository: FinalReportRepository
) {

    fun submitFinalReport() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = finalReportRepository.submitFinalReport()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (isSubmitted) {
                                emit(Data(type = EmitType.Navigate, value = Destination.ReportSubmitSuccess))
                                emit(Data(type = EmitType.BackendSuccess, value = message))
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