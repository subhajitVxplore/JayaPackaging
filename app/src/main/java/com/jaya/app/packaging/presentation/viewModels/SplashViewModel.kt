package com.jaya.app.packaging.presentation.viewModels;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.domain.models.AppVersion
import com.jaya.app.core.domain.useCases.SplashUseCases
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.extensions.MyDialog
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.presentation.extensions.castValueToRequiredTypes
import com.jaya.app.packaging.utility.UiData
import com.vxplore.core.common.DialogData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val splashUseCases: SplashUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val versionUpdateDialog = mutableStateOf<MyDialog?>(null)
    val versionUpdateLink = SavableMutableState<String?>(UiData.AppStoreLink,savedStateHandle,null)


    init {
        getBaseUrl()
    }
    private fun getBaseUrl() {
        splashUseCases.getBaseUrl()
            .flowOn(Dispatchers.IO)
            .onEach {
                when (it.type) {
                    EmitType.BaseUrl -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {
                                pref.storeBaseUrl(it)
                                checkAppVersion()
                            }
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
    }//getBaseUrl


    private suspend fun checkAppVersion() {
        splashUseCases.checkAppVersion()
            .flowOn(Dispatchers.IO)
            .collect {
                when (it.type) {
                    EmitType.BackendError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {

                            }
                        }
                    }

                    EmitType.AppVersion -> {
                        it.value?.apply {
                            castValueToRequiredTypes<AppVersion>()?.let { appVersion ->
                                versionUpdateDialog.value = MyDialog(
                                    data = DialogData(
                                        title = "Version Update",
                                        message = appVersion.versionMessage,
                                        positive = "Update Now",
                                        negative = "Update Later",
                                        data = appVersion
                                    )
                                )
                                handleDialogEvents()
                            }
                        }
                    }

                    EmitType.Navigate -> {
                        it.value?.apply {

                            castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let { destination ->
                                appNavigator.tryNavigateTo(
                                    destination(),
                                    popUpToRoute = Destination.Splash(),
                                    isSingleTop = true,
                                    inclusive = true
                                )
                            }

                        }


                    }

                    else -> {}
                }
            }
    }//checkAppVersion


    private fun handleDialogEvents() {
        versionUpdateDialog.value?.onConfirm = {
            it?.castValueToRequiredTypes<AppVersion>()?.apply {
                versionUpdateLink.setValue(link)
            }
        }
        versionUpdateDialog.value?.onDismiss = {
            versionUpdateDialog.value?.setState(MyDialog.Companion.State.DISABLE)
            splashUseCases.navigateToAppropiateScreen().onEach {
                when (it.type) {
                    EmitType.Navigate -> {
                        it.value?.apply {
                            castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let {
                                appNavigator.navigateTo(
                                    it(),
                                    popUpToRoute = Destination.Splash(),
                                    inclusive = true
                                )
                            }
                        }
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }//handleDialogEvents




    fun onSplashToLogin() {
        appNavigator.tryNavigateTo(
            route = Destination.Login(),
            popUpToRoute = Destination.Splash(),
            isSingleTop = true,
            inclusive = true
        )
    }//onSplashToLogin
}//SplashViewModel


