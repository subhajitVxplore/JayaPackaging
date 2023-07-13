package com.jaya.app.packaging.presentation.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.models.AddProductInfoRequest
import com.jaya.app.core.domain.models.Batches
import com.jaya.app.core.domain.models.ProductType
import com.jaya.app.core.domain.useCases.ProductionReportUseCases
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.extensions.castListToRequiredTypes
import com.jaya.app.packaging.extensions.castValueToRequiredTypes
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.utility.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductionReportViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val productionReportUseCases: ProductionReportUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var emailText = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var successMessage = mutableStateOf("")
    var color = mutableStateOf(Color.Gray)
    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)

    var showProductionPackedDialog = mutableStateOf(false)

    var packedGmsText= mutableStateOf("")
    var packedUnitText= mutableStateOf("")
    var packedCbText= mutableStateOf("")

    var batchName= mutableStateOf("")
    var productName= mutableStateOf("")
    //var packedDetailsList= mutableStateListOf<String>()
    var packedDetailsList= mutableStateListOf<AddProductInfoRequest>()

    var totalWeight= mutableStateOf(0f)
    var totalWeightTxt= mutableStateOf("")

    private val _batchList = MutableStateFlow(emptyList<Batches>())
    val batchList = _batchList.asStateFlow()


//    private val _successMessage = MutableStateFlow("")
//    val successMessage = _successMessage.asStateFlow()

    init {
        getBatches()
    }


    suspend fun popScreen() {
        appNavigator.navigateBack(
            route = Destination.Dashboard(),
            inclusive = false
        )
    }


    fun onBack() {
        appNavigator.tryNavigateTo(
            route = Destination.FinalReport(),
            popUpToRoute = Destination.ProductionReport(),
            isSingleTop = true,
            inclusive = true
        )
    }


    private fun getBatches() {
        productionReportUseCases.getBatches()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.BATCH_LIST -> {
                        it.value?.castListToRequiredTypes<Batches>()?.let { batches ->
                            _batchList.update { batches }
                        }
                    }

                    EmitType.NetworkError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {

                            }
                        }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
    }


}


