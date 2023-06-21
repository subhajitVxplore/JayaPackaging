package com.jaya.app.packaging

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.jaya.app.packaging.navigation.MainNavGraph
import com.jaya.app.packaging.presentation.extensions.changeStatusBarColor
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.ui.theme.JayaPackagingTheme
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val baseViewModel by viewModels<BaseViewModel>()
    @RequiresApi(Build.VERSION_CODES.P)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        //changeStatusBarColor(0xFF4BB26D)
      //  window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        super.onCreate(savedInstanceState)
//        val manager = this.packageManager
//        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
//        baseViewModel.versionCode.value=info.versionCode

        setContent {
            JayaPackagingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(Color.White),
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
}


