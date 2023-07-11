package com.jaya.app.packaging.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.models.AvailablePlantShift
import com.jaya.app.core.domain.models.Packaging
import com.jaya.app.core.domain.models.Plant
import com.jaya.app.core.domain.models.RunningShift
import com.jaya.app.core.domain.models.UserData
import com.jaya.app.core.domain.useCases.DashboardUseCases
import com.jaya.app.packaging.extensions.MyDialog
import com.jaya.app.packaging.extensions.castListToRequiredTypes
import com.jaya.app.packaging.extensions.castValueToRequiredTypes
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.ui.theme.SplashGreen
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashBoardUseCases: DashboardUseCases,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val dataLoading = SavableMutableState(UiData.StateApiLoading, savedStateHandle, false)
    var userName = mutableStateOf("")
    var userId = mutableStateOf("")
    var emailId = mutableStateOf("")
    var designation = mutableStateOf("")
    var packagingShift = mutableStateOf("")
    var packagingPlant = mutableStateOf("")

    private val _runningShiftList = MutableStateFlow(emptyList<RunningShift>())
    val runningShiftList = _runningShiftList.asStateFlow()

    private val _availableShiftList = MutableStateFlow(emptyList<AvailablePlantShift>())
    val availableShiftList = _availableShiftList.asStateFlow()

    private val _plantList = MutableStateFlow(emptyList<Plant>())
    val plantList = _plantList.asStateFlow()


    val dashboardBack = mutableStateOf<MyDialog?>(null)


    var selectedShiftBtnStatus = mutableStateOf(false)
    var selectedShiftBtnName = mutableStateOf("A")
    var selectedPlantName = mutableStateOf("1")

    var isShiftAselected = mutableStateOf(true)
    var shiftABtnBackColor = mutableStateOf(SplashGreen)
    var shiftATxtColor = mutableStateOf(Color.White)

    var isShiftBselected = mutableStateOf(false)
    var shiftBBtnBackColor = mutableStateOf(Color.White)
    var shiftBTxtColor = mutableStateOf(Color.DarkGray)

    var isShiftCselected = mutableStateOf(false)
    var shiftCBtnBackColor = mutableStateOf(Color.White)
    var shiftCTxtColor = mutableStateOf(Color.DarkGray)
    var uploadProofBtnTxt = mutableStateOf("   Upload Proof")
    var addProductionInfoBtnTxt = mutableStateOf("   Add Production Info")

    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)
    //var isHomePageShow = mutableStateOf(true)
    var showVideoImageDialog = mutableStateOf(false)

    var cameraImageFlag = mutableStateOf(false)


    init {
        getUserDetails()
        getRunningShiftList()
        getAvailablePlantShiftList()
        getPlants()
        //isShiftAselected.value=true
    }


    fun onImageCaptureToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            //popUpToRoute = Destination.CaptureVideo(),
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

    fun onHomePageToAddProductInfo() {
        appNavigator.tryNavigateTo(
            route = Destination.ProductionReport(),
            // popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onHomePageToReportSubmitSuccess() {
        appNavigator.tryNavigateTo(
            route = Destination.ReportSubmitSuccess(),
            popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onBackDialog() {
        dashboardBack.value = MyDialog(
            data = DialogData(
                title = "Jaya Packaging App",
                message = "Are you sure you want to exit ?",
                positive = "Yes",
                negative = "No",
            )
        )
        handleDialogEvents()
    }

    private fun handleDialogEvents() {
        dashboardBack.value?.onConfirm = {

        }
        dashboardBack.value?.onDismiss = {
            dashboardBack.value?.setState(MyDialog.Companion.State.DISABLE)
        }
    }

    fun onLogoutFromDashboard() {
        viewModelScope.launch {
            dashBoardUseCases.logOutFromDashboard().collect {
                when (it.type) {
                    EmitType.Navigate -> {
                        it.value?.apply {

                            castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let { destination ->
                                appNavigator.tryNavigateTo(
                                    destination(),
                                    popUpToRoute = Destination.Login(),
                                    isSingleTop = true,
                                    inclusive = true
                                )
                            }

                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun getUserDetails() {
        dashBoardUseCases.getUserDetails()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {
                    EmitType.USER_DATA -> {
                        it.value?.castValueToRequiredTypes<UserData>()?.let {
                            userName.value = it.name
                            userId.value = it.user_id
                            emailId.value = it.email
                            designation.value = it.designation
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

    private fun getPlants() {
        dashBoardUseCases.getPlants()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.PLANT_LIST -> {
                        it.value?.castListToRequiredTypes<Plant>()?.let { packaging ->
                            _plantList.update { packaging }
                        }
                    }

                    EmitType.NetworkError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {

                            }
                        }
                    }

                    EmitType.Loading -> {
                        it.value?.apply {
                            castValueToRequiredTypes<Boolean>()?.let {
                                dataLoading.setValue(it)
                            }
                        }
                    }

                    else -> {

                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getRunningShiftList() {
        dashBoardUseCases.getRunningShiftList()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.PACKAGING_LIST -> {
                        it.value?.castListToRequiredTypes<RunningShift>()?.let { packaging ->
                            _runningShiftList.update { packaging }
                        }
                    }

                    EmitType.NetworkError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {

                            }
                        }
                    }

                    EmitType.Loading -> {
                        it.value?.apply {
                            castValueToRequiredTypes<Boolean>()?.let {
                                dataLoading.setValue(it)
                            }
                        }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    private fun getAvailablePlantShiftList() {
        dashBoardUseCases.getAvailablePlantShiftList()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {

                    EmitType.PACKAGING_LIST -> {
                        it.value?.castListToRequiredTypes<AvailablePlantShift>()?.let { packaging ->
                            _availableShiftList.update { packaging }
                        }
                    }

                    EmitType.NetworkError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {

                            }
                        }
                    }

                    EmitType.Loading -> {
                        it.value?.apply {
                            castValueToRequiredTypes<Boolean>()?.let {
                                dataLoading.setValue(it)
                            }
                        }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    fun onHomePageToAddProduct() {
        appNavigator.tryNavigateTo(
            route = Destination.AddProduct(),
            // popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onHomePageToAddPackingDetails() {
        appNavigator.tryNavigateTo(
            route = Destination.AddPackingDetails(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onHomePageToFinalReport() {
        appNavigator.tryNavigateTo(
            route = Destination.FinalReport(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }


}


