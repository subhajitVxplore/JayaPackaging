package com.jaya.app.packaging.presentation.ui.custom_view

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityOptionsCompat

@Composable
fun ImageSection(
    onSelected: (Bitmap?) -> Unit,
    imageSource: State<ImageSource>,
    clearSource: () -> Unit
) {
    val context = LocalContext.current
    val takeFromCamera =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview(),
            onResult = {
                onSelected(it)
                clearSource()
            }
        )

    LaunchedEffect(key1 = imageSource.value) {
        when (imageSource.value) {
            ImageSource.Camera -> takeFromCamera.launch(
                options = ActivityOptionsCompat.makeTaskLaunchBehind()
            )
            ImageSource.None -> {}
        }
    }

}