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
class VideoCaptureViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {




    fun onVideoCaptureToAddProduct() {
        appNavigator.tryNavigateTo(
            route = Destination.AddProduct(),
            popUpToRoute = Destination.CaptureVideo(),
            isSingleTop = true,
            inclusive = true
        )
    }













}


