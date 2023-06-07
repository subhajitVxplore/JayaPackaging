package com.jaya.app.packaging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val baseViewModel by viewModels<BaseViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        //changeStatusBarColor(0xFF4BB26D)
      //  window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        super.onCreate(savedInstanceState)
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JayaPackagingTheme {
        Greeting("Android")
    }
}