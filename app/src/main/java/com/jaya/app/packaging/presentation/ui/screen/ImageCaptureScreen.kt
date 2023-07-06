package com.jaya.app.packaging.presentation.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jaya.app.packaging.BuildConfig
import com.jaya.app.packaging.R
import com.jaya.app.packaging.createImageFile
import com.jaya.app.packaging.extensions.BackPressHandler
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.ImageCaptureViewModel
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
import java.util.Objects

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageCaptureScreen(
    baseViewModel: BaseViewModel,
    viewModel: ImageCaptureViewModel = hiltViewModel(),
) {

    BackPressHandler(onBackPressed = {
        viewModel.onImageCaptureToFinalReport()
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
                modifier = Modifier
                    .width(screenWidth * 0.15f)
                    .align(Alignment.CenterVertically)
                    .clickable(
                        onClick = {
                            viewModel.onImageCaptureToFinalReport()
                        },
                        role = Role.Image
                    )
            )

            Text(
                text = "Capture Image",
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color.DarkGray,
                style = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

        }
//-------------------------------------------------------------------------------------

        val context = LocalContext.current
        val file = context.createImageFile()
        val uri = FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            BuildConfig.APPLICATION_ID + ".provider", file
        )

        var capturedImageUri by remember {
            mutableStateOf<Uri>(Uri.EMPTY)
        }

        val cameraLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                capturedImageUri = uri
            }

        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        if (capturedImageUri.path?.isNotEmpty() == true) {
            val file = File(capturedImageUri.path.toString())
            val imageFile = RequestBody.create("*/*".toMediaTypeOrNull(), file)
            baseViewModel.imageMultipartList.add(MultipartBody.Part.createFormData("packaging_img_file",file.name,imageFile))
            viewModel.onImageCaptureToFinalReport()
        }

        LaunchedEffect(true){
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                // Request a permission
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }

        }

    //        else{
//
//        }



    }//parentColumn
}//VideoCaptureScreen

