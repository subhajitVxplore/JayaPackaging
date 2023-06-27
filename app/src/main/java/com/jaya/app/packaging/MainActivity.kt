package com.jaya.app.packaging

import android.os.Build
import android.os.Bundle
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
}


