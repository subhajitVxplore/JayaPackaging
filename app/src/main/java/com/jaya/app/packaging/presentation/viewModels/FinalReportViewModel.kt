package com.jaya.app.packaging.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinalReportViewModel @Inject constructor(
    // private val dashBoardUseCases: DashboardUseCases,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)

    init {

    }


    fun onHomePageToImageCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.ImageCapture(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

fun onHomePageToVideoCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.CaptureVideo(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onVideoCaptureToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.FinalReport(),
            popUpToRoute = Destination.CaptureVideo(),
            isSingleTop = true,
            inclusive = true
        )
    }

}


