package com.jaya.app.packaging.presentation.viewModels

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.domain.models.Packaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FinalReportViewModel @Inject constructor(
    // private val dashBoardUseCases: DashboardUseCases,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var loadingButton = mutableStateOf(true)
    var loadingg = mutableStateOf(false)
    var showImageDialog= mutableStateOf(false)

    private val _capturedImages = MutableStateFlow(emptyList<Bitmap>())
    val capturedImages = _capturedImages.asStateFlow()
    var capturedImagesList= mutableStateListOf<Bitmap>()

    init {

    }

    fun onImageCaptured(bitmap: Bitmap?) {
        //cameraIconClick()
        showImageDialog.value=false
        if (bitmap != null) {
            capturedImagesList.add(bitmap)
            _capturedImages.update {
                val tmp = it.toMutableList()
                tmp.add(bitmap)
                tmp
            }
        }
    }



    fun onGalleryImageCaptured(uri: Uri?){
        showImageDialog.value = false
        if(uri != null){
            if (Build.VERSION.SDK_INT < 28)
            {
                MediaStore.Images.Media.getBitmap(com.jaya.app.packaging.application.JayaPackagingApp.app?.applicationContext?.contentResolver,uri)
            } else {
                val source = com.jaya.app.packaging.application.JayaPackagingApp.app?.applicationContext?.let {
                    ImageDecoder.createSource(it.contentResolver,uri)
                }
                if (source != null) {
                    val bitmapImage = ImageDecoder.decodeBitmap(source)
                    capturedImagesList.add(bitmapImage)
                    _capturedImages.update {
                        val tmp = it.toMutableList()
                        tmp.add(bitmapImage)
                        tmp
                    }
                }
            }
        }
    }



    fun onFinalReportToProductionReport() {
        appNavigator.tryNavigateTo(
            route = Destination.ProductionReport(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }
  fun onHomePageToImageCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.ImageCapture(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

fun onHomePageToVideoCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.CaptureVideo(),
            //popUpToRoute = Destination.Dashboard(),
            isSingleTop = true,
            inclusive = true
        )
    }

    fun onFinalReportToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.FinalReport(),
            isSingleTop = true,
            inclusive = true
        )
    }

}


