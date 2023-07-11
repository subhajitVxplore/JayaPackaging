package com.jaya.app.packaging.presentation.ui.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.jaya.app.packaging.R
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.presentation.ui.custom_view.ImageCaptureDialog
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.FinalReportViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.LogoutRed
import com.jaya.app.packaging.ui.theme.SplashGreen

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FinalReportScreen(
    baseViewModel: BaseViewModel,
    viewModel: FinalReportViewModel = hiltViewModel(),
) {

    if (viewModel.showImageDialog.value)
        ImageCaptureDialog(
            setShowDialog = { viewModel.showImageDialog.value = it },
            onImageCaptured = viewModel::onImageCaptured,
            onGalleryImageCapturd = viewModel::onGalleryImageCaptured
        )

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
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            viewModel.onFinalReportToDashboard()
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
//--------------------------------------------------------------------------------------------------

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
                    top = 20.dp,
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
                            text = "Plant ${baseViewModel.plant.value} - Shift ${baseViewModel.shift.value}",
                            modifier = Modifier.weight(1f),
                            //   .wrapContentSize(),
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${baseViewModel.date.value}",
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
                            text = "${baseViewModel.mixingSupervisor.value}",
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
                            text = "${baseViewModel.productType.value}",
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
                            text = "Packing Supervisor : ",
                            modifier = Modifier
                                .wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            //textAlign =
                        )
                        Text(
                            text = "${baseViewModel.packingSupervisor.value}",
                            modifier = Modifier.wrapContentSize(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                            //textAlign =
                        )
                    }


                }

            }//column
        }//card

        Button(
            onClick = {
                //   Toast.makeText(context, "continue", Toast.LENGTH_SHORT).show()
                viewModel.onFinalReportToProductionReport()
            },
            enabled = viewModel.loadingButton.value,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 15.dp
                )
                .fillMaxWidth()
                .height(53.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            if (!viewModel.loadingg.value) {
                Text(
                    text = "Add Production Info",
                    color = Color.White,
                    fontSize = 18.sp,
                )
            } else {
                CircularProgressIndicator(color = Color.White)
            }
        }
//----------------------------------------------------------------------------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            //horizontalArrangement = Arrangement.SpaceBetween,
            // verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.padding(top = 20.dp)) {
                // CaptureImage(viewModel = viewModel, baseViewModel = baseViewModel)//ImageSource`.Camera)
                val context= LocalContext.current
                Card(
                    border = BorderStroke(2.dp, Color.DarkGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            // ImageSource.Camera
                            if (viewModel.capturedImagesList.size < 3){
                                viewModel.showImageDialog.value = true
                            }else{
                                Toast
                                    .makeText(context, "Maximum image limit is Three(3)", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                        // .align(Alignment.End)
                        .width(80.dp)
                        .height(80.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.baseline_photo_camera),
                            contentDescription = "",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp),
                            colorFilter = ColorFilter.tint(Color.White),
                            alignment = Alignment.Center
                        )

                    }
                }
                Text(
                    text = "Image",
                    modifier = Modifier
                        .padding(start=15.dp,top = 5.dp, end = 40.dp),
                    // .align(Alignment.End),
                    color = Color.DarkGray,
                    style = LocalTextStyle.current.copy(fontSize = 15.sp)
                )

                var imageList=viewModel.capturedImages.collectAsState().value

                for ((index, imageClips) in viewModel.capturedImagesList.withIndex()) {
//                    Column(
//                        modifier = Modifier.fillMaxHeight()
//                            .wrapContentWidth().align(Alignment.End)
//                            .padding(bottom = 70.dp)
//                            .verticalScroll(rememberScrollState())
//                    ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 10.dp)
                            .wrapContentSize()
                    ) {
                        Card(
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(5.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .wrapContentSize(),
                        ) {
                            Text(
                                text = "Image_${index+1}",
                                color = Color.DarkGray,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 5.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                            )
                        }//card
                        Image(
                            painter = painterResource(R.drawable.delete_labour_svg),
                            contentDescription = "close button",
                            colorFilter = ColorFilter.tint(LogoutRed),
                            modifier = Modifier
                                .clickable { viewModel.capturedImagesList.removeAt(index) }
                                .height(30.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 5.dp)
                        )
                    }//row
                }
            }
//--------------------------------------------------------------------------------------------------------------------
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .weight(1f)
            ) {
                val context = LocalContext.current
                var uploadVideoTxt by remember("Capture Video") { mutableStateOf("Capture Video") }
                var imageCardBorderColor by remember(Color.Gray) { mutableStateOf(Color.Gray) }
                Card(
                    border = BorderStroke(2.dp, imageCardBorderColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            if (baseViewModel.videoMultipartList.size < 3) {
                                viewModel.onHomePageToVideoCapture()
                            } else {
                                Toast
                                    .makeText(context, "Maximum video limit is Three(3)", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                        .align(Alignment.End)
                        .width(80.dp)
                        .height(80.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.baseline_videocam),
                            contentDescription = "",
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp),
                            colorFilter = ColorFilter.tint(Color.White),
                            alignment = Alignment.Center
                        )
                        imageCardBorderColor = Color.DarkGray
                        uploadVideoTxt = "Video"
                    }
                }
                Text(
                    text = uploadVideoTxt,
                    modifier = Modifier
                        .padding(top = 5.dp, end = 40.dp)
                        .align(Alignment.End),
                    color = Color.DarkGray,
                    style = LocalTextStyle.current.copy(fontSize = 15.sp)
                )

                for ((index, videoClips) in baseViewModel.videoMultipartList.withIndex()) {
//                    Column(
//                        modifier = Modifier.fillMaxHeight()
//                            .wrapContentWidth().align(Alignment.End)
//                            .padding(bottom = 70.dp)
//                            .verticalScroll(rememberScrollState())
//                    ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 10.dp, end = 10.dp)
                            .wrapContentSize()
                    ) {
                        Card(
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(5.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .wrapContentSize(),
                        ) {
                            Text(
                                text = "Video_${index+1}",
                                color = Color.DarkGray,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 5.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                            )
                        }//card
                        Image(
                            painter = painterResource(R.drawable.delete_labour_svg),
                            contentDescription = "close button",
                            colorFilter = ColorFilter.tint(LogoutRed),
                            modifier = Modifier
                                .clickable { baseViewModel.videoMultipartList.removeAt(index) }
                                .height(30.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 5.dp)
                        )

                    }//row
                }
            }//for

            // }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            Button(
                onClick = {
                    //   Toast.makeText(context, "continue", Toast.LENGTH_SHORT).show()
                    //viewModel.onHomePageToReportSubmitSuccess()
                          viewModel.submitFinalReport()
                },
                enabled = viewModel.loadingButton.value,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 15.dp,
                        bottom = 20.dp
                    )
                    .fillMaxWidth()
                    .height(53.dp),
                colors = ButtonDefaults.buttonColors(SplashGreen)
            ) {
                if (!viewModel.loadingg.value) {
                    Text(
                        text = "Submit Report",
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                } else {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
//        ImageSection(
//            onSelected = viewModel::onInvoiceSelected,
//            imageSource = viewModel.selectedImageSource,
//            clearSource = { viewModel.selectedImageSource.value = ImageSource.None }
//        )
    }//parentColumn
}//VideoCaptureScreen



