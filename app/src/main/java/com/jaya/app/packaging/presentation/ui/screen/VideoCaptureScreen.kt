package com.jaya.app.packaging.presentation.ui.screen

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jaya.app.packaging.utility.createVideoCaptureUseCase
import com.jaya.app.packaging.utility.startRecordingVideo
import kotlinx.coroutines.launch
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.extensions.screenWidth
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.VideoCaptureViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.SplashGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VideoCaptureScreen(
    baseViewModel: BaseViewModel,
viewModel: VideoCaptureViewModel= hiltViewModel(),
) {

    BackPressHandler(onBackPressed = {
        viewModel.onVideoCaptureToAddProduct()
        //  Toast.makeText(context, "context", Toast.LENGTH_SHORT).show()
    })

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(AppBarYellow)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_svg),
                contentDescription = null,
                // tint = Color.Gray,
                modifier = Modifier
                    .width(screenWidth * 0.15f)
                    .align(Alignment.CenterVertically)
                    .clickable(
                        onClick = {
                            viewModel.onVideoCaptureToAddProduct()
                        },
                        role = Role.Image
                    )
            )

            Text(
                text = "Capture Video",
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color.DarkGray,
                style = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

        }
//---------------------

        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current

        val permissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )
        )


        var recording: Recording? = remember { null }
        val previewView: PreviewView = remember { PreviewView(context) }
        val videoCapture: MutableState<VideoCapture<Recorder>?> = remember { mutableStateOf(null) }
        val recordingStarted: MutableState<Boolean> = remember { mutableStateOf(false) }

        val audioEnabled: MutableState<Boolean> = remember { mutableStateOf(false) }
        val cameraSelector: MutableState<CameraSelector> = remember {
            mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA)
        }

        LaunchedEffect(Unit) {
            permissionState.launchMultiplePermissionRequest()
        }

        LaunchedEffect(previewView) {
            videoCapture.value = context.createVideoCaptureUseCase(
                lifecycleOwner = lifecycleOwner,
                cameraSelector = cameraSelector.value,
                previewView = previewView
            )
        }
        PermissionsRequired(
            multiplePermissionsState = permissionState,
            permissionsNotGrantedContent = { /* ... */ },
            permissionsNotAvailableContent = { /* ... */ }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AndroidView(
                    factory = { previewView },
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = {
                        if (!recordingStarted.value) {
                            videoCapture.value?.let { videoCapture ->
                                recordingStarted.value = true
                                val mediaDir = context.externalCacheDirs.firstOrNull()?.let {
                                    File(
                                        it,
                                        context.getString(R.string.app_name)
                                    ).apply { mkdirs() }
                                }

                                recording = startRecordingVideo(
                                    context = context,
                                    filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
                                    videoCapture = videoCapture,
                                    outputDirectory = if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir,
                                    executor = context.mainExecutor,
                                    audioEnabled = audioEnabled.value
                                ) { event ->
                                    if (event is VideoRecordEvent.Finalize) {
                                        val uri = event.outputResults.outputUri
                                        Log.d("VideoCaptureScreen", "VideoCaptureScreen:$uri ")
                                        if (uri != Uri.EMPTY) {

                                            baseViewModel.videoUri=uri
                                            viewModel.onVideoCaptureToAddProduct()

//                                            for ((index, videoClips) in baseViewModel.videoClipList.withIndex()) {
//                                                baseViewModel.videoClipList[index]=uri.toString()
//                                            }
//                                            val uriEncoded = URLEncoder.encode(
//                                                uri.toString(),
//                                                StandardCharsets.UTF_8.toString()
//                                            )
                                            // navController.navigate("${Route.VIDEO_PREVIEW}/$uriEncoded")
                                        }
                                    }
                                }
                            }
                        } else {
                            recordingStarted.value = false
                            recording?.stop()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp)
                ) {
                    Icon(
                        painter = painterResource(if (recordingStarted.value) R.drawable.ic_stop else R.drawable.ic_record),
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = SplashGreen
                    )
                }
                if (!recordingStarted.value) {
                    IconButton(
                        onClick = {
                            audioEnabled.value = !audioEnabled.value
                        },
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 32.dp)
                    ) {
                        Icon(
                            painter = painterResource(if (audioEnabled.value) R.drawable.ic_mic_on else R.drawable.ic_mic_off),
                            contentDescription = "",
                            modifier = Modifier.size(50.dp),
                            tint = SplashGreen
                        )
                    }
                }
                if (!recordingStarted.value) {
                    IconButton(
                        onClick = {
                            cameraSelector.value =
                                if (cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
                                else CameraSelector.DEFAULT_BACK_CAMERA
                            lifecycleOwner.lifecycleScope.launch {
                                videoCapture.value = context.createVideoCaptureUseCase(
                                    lifecycleOwner = lifecycleOwner,
                                    cameraSelector = cameraSelector.value,
                                    previewView = previewView
                                )
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 32.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_switch_camera),
                            contentDescription = "",
                            modifier = Modifier.size(50.dp),
                            tint = SplashGreen
                        )
                    }
                }
            }//Box
        }//PermissionsRequired
    }//parentColumn
}//VideoCaptureScreen


//////////////////////////////////////////////////////////////////////
@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}