package com.jaya.app.packaging.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.useCases.DashboardUseCases
import com.jaya.app.packaging.presentation.extensions.castValueToRequiredTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashBoardUseCases: DashboardUseCases,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {



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


    fun onHomePageToAddProduct() {
        appNavigator.tryNavigateTo(
            route = Destination.AddProduct(),
           // popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }


}


