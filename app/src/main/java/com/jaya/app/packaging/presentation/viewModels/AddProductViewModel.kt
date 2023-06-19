package com.jaya.app.packaging.presentation.viewModels;

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.utility.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
   // private val splashUseCases: SplashUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var productName= mutableStateOf("")
    var packingName= mutableStateOf("")
    var batchNumber= mutableStateOf("")
    var selectedProduct= mutableStateOf("Choose Product")

    var startTimeSelected = mutableStateOf("Start Time")
    var endTimeSelected = mutableStateOf("End Time")


    var videoClipList= mutableStateListOf(0)

    init {

    }



    fun onAddProductToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.AddProduct(),
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









}


