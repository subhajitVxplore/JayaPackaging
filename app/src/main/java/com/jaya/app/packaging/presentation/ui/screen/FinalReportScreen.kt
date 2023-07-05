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
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
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
fun FinalReportScreen(
    baseViewModel: BaseViewModel,
    viewModel: VideoCaptureViewModel = hiltViewModel(),
) {

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
                            viewModel.onVideoCaptureToDashboard()
                        },
                        role = Role.Image
                    )
            )

            Text(
                text = "Final Report",
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color.DarkGray,
                style = LocalTextStyle.current.copy(fontSize = 20.sp)
            )

        }
//---------------------

        Card(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 450,
                        easing = LinearOutSlowInEasing
                    )
                )
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 10.dp,
                    bottom = 15.dp
                ),
//                .clickable {
//                    //Toast.makeText(context,"hello$index",Toast.LENGTH_SHORT).show()
//                    viewModel.onHomePageToFinalReport()
//                },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(200.dp)

            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 20.dp
                            )
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = "Plant 1 - Shift A",
                            modifier = Modifier.weight(1f),
                            //   .wrapContentSize(),
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "02/07/2023",
                            modifier = Modifier
                                .wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }

                    Row(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 15.dp
                        )
                    ) {
                        Text(
                            text = "Mixing Supervisor : ",
                            modifier = Modifier
                                .wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            //textAlign =
                        )
                        Text(
                            text = "Suman Sarkar",
                            modifier = Modifier.wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                            //textAlign =
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color.DarkGray)
                    ) {
                        Text(
                            text = "Dream Marie",
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 20.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 15.dp
                        )
                    ) {
                        Text(
                            text = "Mixing Supervisor : ",
                            modifier = Modifier
                                .wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            //textAlign =
                        )
                        Text(
                            text = "Suman Sarkar",
                            modifier = Modifier.wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                            //textAlign =
                        )
                    }



                }

            }//column
        }


    }//parentColumn
}//VideoCaptureScreen

