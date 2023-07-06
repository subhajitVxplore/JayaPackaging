package com.jaya.app.packaging

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.jaya.app.packaging.navigation.MainNavGraph
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.ui.theme.JayaPackagingTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val baseViewModel by viewModels<BaseViewModel>()
    @RequiresApi(Build.VERSION_CODES.P)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JayaPackagingTheme(baseViewModel) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) { paddingValues ->
                    CompositionLocalProvider() {
                        MainNavGraph(
                            navHostController = rememberNavController(),
                            navigationChannel = baseViewModel.appNavigator.navigationChannel,
                            paddingValues = paddingValues,
                            baseViewModel = baseViewModel,
                        )
                    }
                }
            }
        }
    }
}//MainActivity

//==================================================================================================

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir /* directory */
    )
  //  Toast.makeText(applicationContext, "+++++", Toast.LENGTH_SHORT).show()

    return image
}//Context.createImageFile()


