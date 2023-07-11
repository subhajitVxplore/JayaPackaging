package com.jaya.app.packaging.presentation.viewModels;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.models.AvailablePackersNumber
import com.jaya.app.core.domain.models.AvailablePackingMistri
import com.jaya.app.core.domain.models.AvailablePackingOperator
import com.jaya.app.core.domain.models.PackingLabour
import com.jaya.app.core.domain.models.ProductType
import com.jaya.app.core.domain.useCases.AddPackingDetailsUseCases
import com.jaya.app.core.domain.useCases.AddProductUseCases
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.extensions.MyDialog
import com.jaya.app.packaging.extensions.castListToRequiredTypes
import com.jaya.app.packaging.extensions.castValueToRequiredTypes
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.utility.UiData
import com.vxplore.core.common.DialogData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class AddPackingDetailsViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val addPackingDetailsUseCases: AddPackingDetailsUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var productName = mutableStateOf("")
    var packingSupervisorName = mutableStateOf("")

    var selectedPackingMistri = mutableStateOf("Packing Mistri")
    var selectedPackingOperator = mutableStateOf("Packing Operator")
    var selectedPackersNumber = mutableStateOf("Packers Number")

    var packingLabourList = mutableStateListOf<String>()

    var isSuggestionVisible = mutableStateOf(false)

    private val _packingMistriList = MutableStateFlow<List<AvailablePackingMistri>?>(null)
    val packingMistriList = _packingMistriList.asStateFlow()

    private val _packingOperatorList = MutableStateFlow<List<AvailablePackingOperator>?>(null)
    val packingOperatorList = _packingOperatorList.asStateFlow()

    private val _packersNumberList = MutableStateFlow<List<AvailablePackersNumber>?>(null)
    val packersNumberList = _packersNumberList.asStateFlow()

//    private val _packingLabours = MutableStateFlow<List<PackingLabour>?>(null)
//    val packingLabours = _packingLabours.asStateFlow()

    private val _packingLabours = MutableStateFlow(emptyList<PackingLabour>())
    var packingLabours = _packingLabours.asStateFlow()

    var backendMessage = mutableStateOf("")
    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)
    val saveDetailsDialog = mutableStateOf<MyDialog?>(null)

    var packingLabourSearchQuery = mutableStateOf("")
//    var packingLabourSearchQuery by mutableStateOf(TextFieldValue())
//        private set


    init {
        // getProductTypes()
        getAvailableWorkersList()
        getPackingLabourList()
    }


    fun openSuggestions() {
        isSuggestionVisible.value = !isSuggestionVisible.value
    }

    fun onSaveProductDialog() {
        saveDetailsDialog.value = MyDialog(
            data = DialogData(
                title = "Jaya Packaging App",
                message = "Are you sure you want save the details?",
                positive = "Yes",
                negative = "No",
            )
        )
        handleDialogEvents()
    }

    private fun handleDialogEvents() {
        saveDetailsDialog.value?.onConfirm = {
        }
        saveDetailsDialog.value?.onDismiss = {
            saveDetailsDialog.value?.setState(MyDialog.Companion.State.DISABLE)
        }
    }


    fun onAddPackingDetailsToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.AddPackingDetails(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onUploadVideoToVideoCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.CaptureVideo(),
            //  popUpToRoute = Destination.AddProduct(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onBack() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.AddPackingDetails(),
            isSingleTop = true,
            inclusive = true
        )
    }

    suspend fun popScreen() {
        appNavigator.navigateBack(
            route = Destination.Dashboard(),
            inclusive = false
        )
    }


    fun getAvailableWorkersList() {
        addPackingDetailsUseCases.getAvailableWorkersList()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.PACKING_MISTRI_LIST -> {
                        it.value?.castListToRequiredTypes<AvailablePackingMistri>()
                            ?.let { products ->
                                _packingMistriList.update { products }
                            }
                    }

                    EmitType.PACKING_OPERATOR_LIST -> {
                        it.value?.castListToRequiredTypes<AvailablePackingOperator>()
                            ?.let { products ->
                                _packingOperatorList.update { products }
                            }
                    }

                    EmitType.PACKING_NUMBER_LIST -> {
                        it.value?.castListToRequiredTypes<AvailablePackersNumber>()
                            ?.let { products ->
                                _packersNumberList.update { products }
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

    fun getPackingLabourList() {
        addPackingDetailsUseCases.getPackingLabourList()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.PACKING_LABOUR_LIST -> {
                        it.value?.castListToRequiredTypes<PackingLabour>()
                            ?.let { products ->
                                _packingLabours.update { products }
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

    fun addPackingDetails() {
        addPackingDetailsUseCases.addPackingDetails()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.Navigate -> {
                        it.value?.apply {

                            castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let { destination ->
                                appNavigator.tryNavigateTo(
                                    destination(),
                                    popUpToRoute = Destination.AddPackingDetails(),
                                    isSingleTop = true,
                                    inclusive = true
                                )
                            }
                        }
                    }

                    EmitType.Loading -> {
                        it.value?.castValueToRequiredTypes<Boolean>()?.let {
                            loadingButton.value = it
                        }
                    }

                    EmitType.BackendSuccess -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {
                                backendMessage.value = it
                            }
                        }
                    }

                    EmitType.NetworkError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {
                                backendMessage.value = it
                            }
                        }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
    }


}


