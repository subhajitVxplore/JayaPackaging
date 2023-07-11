package com.jaya.app.core.domain.useCases

import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.common.Resource
import com.jaya.app.core.common.handleFailedResponse
import com.jaya.app.core.domain.repositories.AddPackingDetailsRepository
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.helpers.AppStore
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class AddPackingDetailsUseCases @Inject constructor(
    private val appStore: AppStore,
    private val addPackingDetailsRepository: AddPackingDetailsRepository
) {






    fun getAvailableWorkersList() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = addPackingDetailsRepository.getAvailableWorkersList()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.PACKING_MISTRI_LIST, value = available_packing_mistri_list))
                            emit(Data(type = EmitType.PACKING_OPERATOR_LIST, value = available_packing_operator_list))
                            emit(Data(type = EmitType.PACKING_NUMBER_LIST, value = available_packers_number_list))
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

    fun getPackingLabourList() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = addPackingDetailsRepository.getPackingLabourList()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.PACKING_LABOUR_LIST, value = packing_labour_list))

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

    fun addPackingDetails() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = addPackingDetailsRepository.addPackingDetails()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (isAdded) {
                                emit(Data(type = EmitType.Navigate, value = Destination.Dashboard))
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