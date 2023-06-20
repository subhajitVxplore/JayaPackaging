package com.jaya.app.packaging.presentation.viewModels;

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.utils.AppNavigator
import com.jaya.app.core.common.Destination
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.helpers_impl.SavableMutableState
import com.jaya.app.packaging.presentation.ui.custom_view.VideoUploadingModel
import com.jaya.app.packaging.utility.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
   // private val splashUseCases: SplashUseCases,
    private val pref: AppStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = SavableMutableState(UiData.LOADER, savedStateHandle, false)
    var productName= mutableStateOf("")
    var packingName= mutableStateOf("")
    var batchNumber= mutableStateOf("")
    var selectedProduct= mutableStateOf("Choose Product")

    var startTimeSelected = mutableStateOf("Start Time")
    var endTimeSelected = mutableStateOf("End Time")


    var videoClipIndex= mutableStateListOf(0)
    //var videoUriList=listOf<Uri?>(null)
    //var videoUriList=listOf<String>("uri-1","uri-2","uri-3","uri-4","uri-5")
    var videoUriList=listOf<Uri?>(null)

    private val _videoClipList = MutableStateFlow<MutableList<VideoUploadingModel>?>(null)
    val videoClipList = _videoClipList.asStateFlow()

    init {

    }

    fun onChangeVideoList(position: Int, videoUploadingModel: VideoUploadingModel) {
        _videoClipList.update {
            val tmp = it?.toMutableList()
            if (tmp != null) {
                tmp[position] = videoUploadingModel
                return@update tmp
            }
            it
        }
    }
//    _videoClipList.compareAndSet(null, MutableList(videoClipSize.size) {
//        PaperWithQuantity()
//    })





    fun onAddProductToDashboard() {
        appNavigator.tryNavigateTo(
            route = Destination.Dashboard(),
            popUpToRoute = Destination.AddProduct(),
            isSingleTop = true,
            inclusive = true
        )
    }

 fun onUploadVideoToVideoCapture() {
        appNavigator.tryNavigateTo(
            route = Destination.CaptureVideo(),
          //  popUpToRoute = Destination.AddProduct(),
            isSingleTop = true,
            inclusive = true
        )
    }

    suspend fun popScreen() {
        appNavigator.navigateBack(
            route = Destination.Dashboard(),
            inclusive = false
        )
    }









}


