package com.jaya.app.packaging.presentation.ui.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.packaging.R.drawable
import com.jaya.app.packaging.presentation.viewModels.SplashViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .background(SplashGreen)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {

        val scale = remember {
            Animatable(0f)
        }
        // AnimationEffect
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            delay(3000L)
            //navController.navigate(AppRoutes.MOBILE_NO)
            viewModel.onSplashToLogin()
        }

        // Image
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.wrapContentSize()) {
                Image(
                    painter = painterResource(drawable.cropped_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .scale(scale.value)
                        .width(250.dp)
                        .height(200.dp),
                )
            }

        }
    }

}