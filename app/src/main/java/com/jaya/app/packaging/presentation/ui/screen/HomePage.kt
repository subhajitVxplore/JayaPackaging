@file:Suppress("DEPRECATION")

package com.jaya.app.packaging.presentation.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.packaging.R
import com.jaya.app.packaging.extensions.bottomToUp
import com.jaya.app.packaging.extensions.screenHeight
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.extensions.upToBottom
import com.jaya.app.packaging.presentation.ui.custom_view.VideoImageDialog
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.DashboardViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen
import io.ktor.util.reflect.*

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun HomePage(
    openDrawer: () -> Unit,
    viewModel: DashboardViewModel,
    baseViewModel: BaseViewModel
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (viewModel.showVideoImageDialog.value)
        VideoImageDialog(
            value = "", setShowDialog = {
                viewModel.showVideoImageDialog.value = it
            },
            onButtonClick = {
                //  viewModel.checkIfDeviceFound()
            },
            viewModel,
            baseViewModel
        )

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.weight(1f),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.isHomePageShow.value = false
                    },
                    modifier = Modifier.size(55.dp),
                    //backgroundColor = WhiteGray,
                    contentColor = Color.White,
                    containerColor = SplashGreen,
                    shape = RoundedCornerShape(200.dp)

                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                AnimatedContent(
                    targetState = viewModel.dataLoading.value,
                    transitionSpec = {
                        if (targetState && !initialState) {
                            upToBottom()
                        } else {
                            bottomToUp()
                        }
                    }
                ) {
                    if (!it) {

                        Column(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            for ((index, packaging) in viewModel.packagingList.collectAsState().value.withIndex()) {
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
                                        )
                                        .clickable {
                                            //Toast.makeText(context,"hello$index",Toast.LENGTH_SHORT).show()
                                            viewModel.onHomePageToFinalReport()
                                        },
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, Color.LightGray),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .height(480.dp)
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

//                                            OutlinedTextField(readOnly = true,
//                                                value = viewModel.uploadProofBtnTxt.value,
//                                                onValueChange = {
//                                                    viewModel.uploadProofBtnTxt.value = it
//                                                },
//                                                modifier = Modifier.focusRequester(focusRequester)
//                                                    .onFocusChanged {
//                                                        if (it.isFocused) {
//                                                            // viewModel.onHomePageToBreakDownReport()
//                                                            viewModel.showVideoImageDialog.value=true
//                                                            focusManager.clearFocus()
//                                                        } else {
//                                                            // not focused
//                                                        }
//                                                    }
//                                                    .fillMaxWidth()
//                                                    .padding(horizontal = 15.dp),
//                                                textStyle = TextStyle.Default.copy(
//                                                    fontSize = 17.sp,
//                                                    fontWeight = FontWeight.Bold
//                                                ),
//                                                colors = TextFieldDefaults.outlinedTextFieldColors(
//                                                    containerColor = Color.LightGray,
//                                                    textColor = Color.DarkGray,
//                                                    unfocusedBorderColor = Color.Gray,
//                                                    focusedBorderColor = Color.Gray
//                                                ),
//                                                leadingIcon = {
//                                                    Icon(
//                                                        painter = painterResource(id = R.drawable.upload_cloud_svg),
//                                                        contentDescription = "",
//                                                        modifier = Modifier.padding(start = 10.dp)
//                                                        // tint = Color.LightGray
//                                                    )
//                                                })

                                            Text(
                                                text = "${baseViewModel.videoMultipartList.size} Video Uploaded",
                                                modifier = Modifier
                                                    .wrapContentSize()
                                                    .padding(start = 20.dp, top = 10.dp),
                                                fontSize = 16.sp,
                                                color = Color.DarkGray,
                                                fontWeight = FontWeight.Bold
                                                //textAlign =
                                            )
                                            Text(
                                                text = "${baseViewModel.imageMultipartList.size} Image Uploaded",
                                                modifier = Modifier
                                                    .wrapContentSize()
                                                    .padding(start = 20.dp, top = 10.dp),
                                                fontSize = 16.sp,
                                                color = Color.DarkGray,
                                                fontWeight = FontWeight.Bold
                                                //textAlign =
                                            )
                                            Button(
                                                onClick = {
                                                    //   Toast.makeText(context, "continue", Toast.LENGTH_SHORT).show()
                                                    viewModel.onHomePageToAddProductInfo()
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
                                            Button(
                                                onClick = {
                                                    //   Toast.makeText(context, "continue", Toast.LENGTH_SHORT).show()
                                                          viewModel.onHomePageToReportSubmitSuccess()
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
                                                colors = ButtonDefaults.buttonColors(Color.DarkGray)
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

                                    }//column
                                }
                            }//for loop

                        }//column(scrollable)
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(
                                        width = screenWidth * 0.15f,
                                        height = screenHeight * 0.15f
                                    )
                                    .padding(bottom = screenHeight * 0.05f),
                                color = SplashGreen,
                                strokeWidth = 5.dp,
                            )
                        }
                    }


                }

            }
        }

    }
}


