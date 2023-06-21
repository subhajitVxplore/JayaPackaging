package com.jaya.app.packaging.presentation.viewModels;

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
class OtpViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
   // private val splashUseCases: SplashUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var isEmailReadOnly= mutableStateOf(true)

    init {

    }




    fun onOtpToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.Otp(),
            isSingleTop = true,
            inclusive = true
        )
    }











}


