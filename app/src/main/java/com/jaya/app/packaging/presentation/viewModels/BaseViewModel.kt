package com.jaya.app.packaging.presentation.viewModels

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.packaging.ui.theme.SplashGreen
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    val appNavigator: AppNavigator,
) : ViewModel() {

    var videoUriList= mutableStateListOf<Uri>()
    var videoMultipartList= mutableStateListOf<MultipartBody.Part>()
    var imageMultipartList= mutableStateListOf<MultipartBody.Part>()
    //var imageMultipartList= mutableStateListOf<String>()
    var videoShootTime= mutableStateListOf<String>()
    var storedLoginEmail= mutableStateOf("")
    var versionCode= mutableStateOf(0)
    var statusBarColor= mutableStateOf(SplashGreen)
        // var videoShootTime= listOf(emptyList<String>())

    suspend fun addImageMultipartToList(imageUri: String) {
        val file = File(imageUri)
        val imageFile = RequestBody.create("*/*".toMediaTypeOrNull(), file)
        imageMultipartList.add(MultipartBody.Part.createFormData("packaging_img_file",file.name,imageFile)
        )
    }


}