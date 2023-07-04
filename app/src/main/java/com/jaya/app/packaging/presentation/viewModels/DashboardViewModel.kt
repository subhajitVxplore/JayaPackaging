package com.jaya.app.packaging.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.models.Packaging
import com.jaya.app.core.domain.models.UserData
import com.jaya.app.core.domain.useCases.DashboardUseCases
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
    private val _packagingList = MutableStateFlow(emptyList<Packaging>())
    val packagingList = _packagingList.asStateFlow()
    val dashboardBack = mutableStateOf<MyDialog?>(null)


    var selectedShiftBtnStatus = mutableStateOf(false)
    var selectedShiftBtnName = mutableStateOf("false")

    var isShiftAselected = mutableStateOf(false)
    var shiftABtnBackColor = mutableStateOf(Color.White)
    var shiftATxtColor = mutableStateOf(Color.DarkGray)

    var isShiftBselected = mutableStateOf(false)
    var shiftBBtnBackColor = mutableStateOf(Color.White)
    var shiftBTxtColor = mutableStateOf(Color.DarkGray)

    var isShiftCselected = mutableStateOf(false)
    var shiftCBtnBackColor = mutableStateOf(Color.White)
    var shiftCTxtColor = mutableStateOf(Color.DarkGray)
    var uploadProofBtnTxt= mutableStateOf("   Upload Proof")
    var addProductionInfoBtnTxt= mutableStateOf("   Add Production Info")

    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)

    var isHomePageShow= mutableStateOf(true)


    init {
        getUserDetails()
        getPackagingList()
        //isShiftAselected.value=true
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

    private fun getPackagingList() {
        dashBoardUseCases.getPackagingList()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {
                    EmitType.PACKAGING_PLANT -> {
                        it.value?.castValueToRequiredTypes<String>()?.let {
                            packagingShift.value = it
                        }
                    }

                    EmitType.PACKAGING_SHIFT -> {
                        it.value?.castValueToRequiredTypes<String>()?.let {
                            packagingPlant.value = it
                        }
                    }

                    EmitType.PACKAGING_LIST -> {
                        it.value?.castListToRequiredTypes<Packaging>()?.let { packaging ->
                            _packagingList.update { packaging }
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
            popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }


}


