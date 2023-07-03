package com.jaya.app.packaging.presentation.ui.screen

import android.content.Intent
import android.net.Uri
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.core.domain.models.AppVersion
import com.jaya.app.packaging.R
import com.jaya.app.packaging.R.drawable
import com.jaya.app.packaging.extensions.OnEffect
import com.jaya.app.packaging.presentation.viewModels.SplashViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.LogoutRed
import com.jaya.app.packaging.ui.theme.SplashGreen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .background(AppBarYellow)
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
            delay(2000L)
            //navController.navigate(AppRoutes.MOBILE_NO)
          //  viewModel.onSplashToLogin()
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
    }//box

    val context = LocalContext.current
    viewModel.versionUpdateDialog.value?.apply {
        if (currentState()) {
            AlertDialog(
                containerColor = AppBarYellow,
                shape = RoundedCornerShape(10.dp),
                onDismissRequest = {
                   // openDialog.value = false
                },
                title = {
                   // Text(text = context.getString(R.string.app_name), color = Color.DarkGray)
                    currentData?.title?.let {
                        Text(text =it,color = Color.DarkGray,fontSize = 20.sp)
                    }
                },
                text = {

                    currentData?.message?.let {
                        Text(text = it,color =Color.Gray,fontSize = 15.sp ) //version message from Api
                    }

                },
                dismissButton = {
                  //  if (!(currentData?.data as AppVersion).isSkipable) {
                        currentData?.negative?.let {
                            Button(
                                onClick = {
                                    onDismiss?.invoke(null)
                                },
                                colors = ButtonDefaults.buttonColors(LogoutRed),
                                shape = RoundedCornerShape(7.dp),
                                modifier = Modifier.padding(end = 10.dp)

                            ) {
                                Text(text = it)
                            }
                        }
                  //  }

                },
                confirmButton = {
                    currentData?.positive?.let {
                        Button(
                            onClick = { onConfirm?.invoke(currentData?.data) },
                            colors = ButtonDefaults.buttonColors(SplashGreen),
                            shape = RoundedCornerShape(7.dp),
                        ) {
                            Text(text = it)
                        }
                    }


                }

            )
        }
    }
    EffectHandlers(viewModel)
}//SplashScreen

//=========================================================
@Composable
private fun EffectHandlers(viewModel: SplashViewModel) {
    val localContext = LocalContext.current

    viewModel.versionUpdateLink.OnEffect(
        intentionalCode = { link->
            link?.let { lk->
                if(lk.isNotEmpty()) {
                    try {
                        val appStoreIntent = Intent(Intent.ACTION_VIEW).also {
                            it.data = Uri.parse(lk)
                        }
                        localContext.startActivity(appStoreIntent)
                    } catch (exception: Exception) {
                        // Toast.makeTextl(localContext, "unable_to_open_play_store", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        clearance = {""}
    )
}