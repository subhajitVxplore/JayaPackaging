package com.jaya.app.packaging.presentation.ui.custom_view

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.jaya.app.packaging.BuildConfig
import com.jaya.app.packaging.R
import com.jaya.app.packaging.createImageFile
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.DashboardViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.Objects

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CaptureImage(viewModel: DashboardViewModel,baseViewModel: BaseViewModel) {

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

    var uploadImageTxt by remember("Capture Image") {mutableStateOf("Capture Image")}
    var imageCardBorderColor by remember(Color.Gray) {mutableStateOf(Color.Gray)}

    Column(modifier = Modifier.padding(top = 20.dp)) {
        Card(
            border = BorderStroke(2.dp, imageCardBorderColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            modifier = Modifier
                .clickable {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                    //viewModel.showVideoImageDialog.value=false
                }
                .align(Alignment.CenterHorizontally)
                .width(90.dp)
                .height(90.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                if (capturedImageUri.path?.isNotEmpty() == true) {
                //  if (capturedImageUri != Uri.EMPTY) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = rememberImagePainter(capturedImageUri),
                        contentDescription = null
                    )
                    imageCardBorderColor= SplashGreen
                    uploadImageTxt="Image Captured"

                    val file = File(capturedImageUri.path.toString())
                    val imageFile = RequestBody.create("*/*".toMediaTypeOrNull(), file)
                    baseViewModel.imageMultipartList.add(MultipartBody.Part.createFormData("packaging_img_file",file.name,imageFile))
                    viewModel.showVideoImageDialog.value=false

                }else{
                    Image(
                        painterResource(id = R.drawable.baseline_photo_camera),
                        contentDescription = "",
                        modifier = Modifier.height(60.dp).width(60.dp),
                        colorFilter = ColorFilter.tint(Color.White),
                        alignment = Alignment.Center
                    )
                }

            }

        }
        Text(
            text = uploadImageTxt,
            modifier = Modifier.padding(top = 15.dp),
            color = Color.DarkGray,
            style = LocalTextStyle.current.copy(fontSize = 15.sp)
        )
    }


}//AppContent()