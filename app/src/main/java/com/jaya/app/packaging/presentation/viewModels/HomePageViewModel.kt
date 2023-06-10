package com.jaya.app.packaging.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    // private val dashBoardUseCases: DashboardUseCases,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {



    init {

    }


}


