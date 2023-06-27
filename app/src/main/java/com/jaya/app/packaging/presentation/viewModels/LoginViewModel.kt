package com.jaya.app.packaging.presentation.viewModels;

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.useCases.LoginUseCases
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.extensions.castValueToRequiredTypes
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.utility.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val loginUseCases: LoginUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var emailText = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var successMessage = mutableStateOf("")
    var color = mutableStateOf(Color.Gray)
    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)


//    private val _successMessage = MutableStateFlow("")
//    val successMessage = _successMessage.asStateFlow()

    init {

    }


    fun getOtp() {
       // viewModelScope.launch {
            loginUseCases.getOtp()
                .flowOn(Dispatchers.IO)
                .onEach {
                    when (it.type) {

                        EmitType.BackendSuccess -> {
                            it.value?.castValueToRequiredTypes<String>()?.let {
                                    successMessage.value = it
                                    Log.d("getOtp", "getOtp: ${successMessage.value}++++")
                                    //_successMessage.update { it }

                                }

                        }

                        EmitType.BackendError -> {
                            it.value?.apply {
                                castValueToRequiredTypes<String>()?.let {
                                    errorMessage.value = it
                                }
                            }
                        }
                        EmitType.Loading -> {
                            it.value?.castValueToRequiredTypes<Boolean>()?.let {
                                loadingButton.value = it
                            }
                        }

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
                }.launchIn(viewModelScope)
       // }
    }


    fun onLoginToOtp() {
        appNavigator.tryNavigateTo(
            route = Destination.Otp(),
            popUpToRoute = Destination.Login(),
            isSingleTop = true,
            inclusive = true
        )
    }


}


