package com.jaya.app.packaging.presentation.viewModels;

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.helpers.AppStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
   // private val splashUseCases: SplashUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {

    }




    fun onSplashToLogin() {
        appNavigator.tryNavigateTo(
            route = Destination.Login(),
            popUpToRoute = Destination.Splash(),
            isSingleTop = true,
            inclusive = true
        )
    }











}


