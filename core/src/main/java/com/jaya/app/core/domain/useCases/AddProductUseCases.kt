package com.jaya.app.core.domain.useCases

import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.common.Resource
import com.jaya.app.core.common.handleFailedResponse
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.helpers.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductUseCases @Inject constructor(
    private val appStore: AppStore,
    private val addProductRepository: AddProductRepository
) {


    fun getProductTypes() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = addProductRepository.getProductTypes()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.PRODUCT_TYPES, value = product_type_list))
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

    fun addProduct() = flow {
        emit(Data(EmitType.Loading, true))
        when (val response = addProductRepository.addProduct()) {//appStore.userId()
            //when (val response =
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (isAdded){
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