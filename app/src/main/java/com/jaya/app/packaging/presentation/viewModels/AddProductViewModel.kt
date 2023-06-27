package com.jaya.app.packaging.presentation.viewModels;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.models.ProductType
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
class AddProductViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val addProductUseCases: AddProductUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var productName = mutableStateOf("")
    var packingName = mutableStateOf("")
    var batchNumber = mutableStateOf("")
    var selectedProduct = mutableStateOf("Choose Product")

    private val _productTypes = MutableStateFlow(emptyList<ProductType>())
    val productTypes = _productTypes.asStateFlow()

    var startTimeSelected = mutableStateOf("Start Time")
    var endTimeSelected = mutableStateOf("End Time")

    var backendMessage = mutableStateOf("")

    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)
    val saveDetailsDialog = mutableStateOf<MyDialog?>(null)


    init {
        getProductTypes()
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


    fun onUploadVideoToVideoCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.CaptureVideo(),
            //  popUpToRoute = Destination.AddProduct(),
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


    private fun getProductTypes() {
        addProductUseCases.getProductTypes()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.PRODUCT_TYPES -> {
                        it.value?.castListToRequiredTypes<ProductType>()?.let { products ->
                            _productTypes.update { products }
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

    fun addProduct() {
        addProductUseCases.addProduct()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.Navigate -> {
                        it.value?.apply {

                            castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let { destination ->
                                appNavigator.tryNavigateTo(
                                    destination(),
                                    popUpToRoute = Destination.AddProduct(),
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

    fun submitPackingDetails(
        product_name: String,
        packing_name: String,
        batch_number: String,
        product_type: String,
        start_time: String,
        end_time: String,
        video_clip_list: List<MultipartBody.Part>
    ) {
        addProductUseCases.submitPackingDetails(
            product_name,
            packing_name,
            batch_number,
            product_type,
            start_time,
            end_time,
            video_clip_list
        )
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.Navigate -> {
                        it.value?.apply {

                            castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let { destination ->
                                appNavigator.tryNavigateTo(
                                    destination(),
                                    popUpToRoute = Destination.AddProduct(),
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


