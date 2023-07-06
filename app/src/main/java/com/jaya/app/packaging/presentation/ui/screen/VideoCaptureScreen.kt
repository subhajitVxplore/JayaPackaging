package com.jaya.app.packaging.presentation.ui.screen

import android.Manifest
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jaya.app.packaging.R
import com.jaya.app.packaging.extensions.BackPressHandler
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.VideoCaptureViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.SplashGreen
import com.jaya.app.packaging.utility.createVideoCaptureUseCase
import com.jaya.app.packaging.utility.startRecordingVideo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VideoCaptureScreen(
    baseViewModel: BaseViewModel,
    viewModel: VideoCaptureViewModel = hiltViewModel(),
) {

    BackPressHandler(onBackPressed = {
        viewModel.onVideoCaptureToFinalReport()
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
                            viewModel.onVideoCaptureToFinalReport()
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

                Button(
                    colors = ButtonDefaults.buttonColors(SplashGreen),
                    onClick = {
                        if (!recordingStarted.value) {
                            videoCapture.value?.let { videoCapture ->

                                val calendarInstance = Calendar.getInstance()
                                val hour = calendarInstance.get(Calendar.HOUR)
                                val minute = calendarInstance.get(Calendar.MINUTE)
                                val seconds = calendarInstance.get(Calendar.SECOND)
                                val ampm = if(calendarInstance.get(Calendar.AM_PM)==0) "AM " else "PM "
                                //baseViewModel.videoShootTime.add("$hour:$minute:$seconds$ampm")
                                //val seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) /1000 %60
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
                                    audioEnabled = true
                                ) { event ->
                                    if (event is VideoRecordEvent.Finalize) {
                                        val uri = event.outputResults.outputUri
                                        if (uri != Uri.EMPTY) {
                                           // baseViewModel.videoUriList.add(uri)
                                            val file = File(uri.path.toString())
                                            val videoFile =RequestBody.create("*/*".toMediaTypeOrNull(), file)
                                            baseViewModel.videoMultipartList.add(MultipartBody.Part.createFormData("packaging_file",file.name,videoFile))

                                            viewModel.onVideoCaptureToFinalReport()
                                        }
                                    }
                                }
                            }
                        } else {
                            recordingStarted.value = false
                            recording?.stop()
                        }

                        val timer = object : CountDownTimer(10000, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                viewModel.timerX.value = (millisUntilFinished / 1000).toString()
                                // Toast.makeText(context, "${viewModel.timerX.value}", Toast.LENGTH_SHORT).show()
                            }
                            override fun onFinish() {
                                recordingStarted.value = false
                                recording?.stop()
                            }
                        }
                        timer.start()

                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = if (viewModel.timerX.value == "") "Start" else "00:0${viewModel.timerX.value}",
                        //text = "00:0${viewModel.timerX.value}",
                        color = Color.White,
                        fontSize = 20.sp,
                    )

                }//IconButton(play/pause)

            }//Box

        }//PermissionsRequired
    }//parentColumn
}//VideoCaptureScreen

