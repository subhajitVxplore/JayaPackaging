package com.jaya.app.packaging.presentation.viewModels

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.packaging.presentation.ui.custom_view.VideoUploadingModel
import com.jaya.app.packaging.ui.theme.SplashGreen
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    val appNavigator: AppNavigator,
) : ViewModel() {

    var videoUriList= mutableStateListOf<Uri>()
    var videoMultipartList= mutableStateListOf<MultipartBody.Part>()
    var videoShootTime= mutableStateListOf<String>()
    var storedLoginEmail= mutableStateOf("")
    var versionCode= mutableStateOf(0)
    var statusBarColor= mutableStateOf(SplashGreen)
        // var videoShootTime= listOf(emptyList<String>())


}