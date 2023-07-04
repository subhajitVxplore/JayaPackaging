package com.jaya.app.packaging.presentation.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.domain.models.AddProductInfoRequest
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.utility.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductionReportViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    //private val loginUseCases: LoginUseCases,
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


//    private val _successMessage = MutableStateFlow("")
//    val successMessage = _successMessage.asStateFlow()

    init {

    }


    suspend fun popScreen() {
        appNavigator.navigateBack(
            route = Destination.Dashboard(),
            inclusive = false
        )
    }


    fun onBack() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }


}


