@file:Suppress("DEPRECATION")

package com.jaya.app.packaging.presentation.ui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.extensions.bottomToUp
import com.jaya.app.packaging.presentation.extensions.screenHeight
import com.jaya.app.packaging.presentation.extensions.screenWidth
import com.jaya.app.packaging.presentation.extensions.upToBottom
import com.jaya.app.packaging.presentation.viewModels.DashboardViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.SplashGreen
import io.ktor.util.reflect.*

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun HomePage(
    openDrawer: () -> Unit,
    viewModel: DashboardViewModel
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.weight(1f),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onHomePageToAddProduct()
//                        Toast
//                            .makeText(context, "addProduct", Toast.LENGTH_SHORT)
//                            .show()
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
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                    Text(
                        text = "Today Status",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp),
                        fontSize = 18.sp,
                        color = Color.Gray,
                        //textAlign =
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 15.dp, end = 20.dp)
                        ) {
                            Text(
                                text = "Plant: ${viewModel.packagingPlant.value}",
                                modifier = Modifier
                                    .wrapContentSize()
                                    //.align(Alignment.CenterVertically)
                                    .padding(end = 7.dp),
                                fontSize = 16.sp,
                                color = Color.Red,

                                //textAlign =
                            )
                            Divider(
                                color = Color.LightGray,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .fillMaxHeight()  //fill the max height
                                    .width(1.dp)
                            )
                            Text(
                                text = "Shift: ${viewModel.packagingShift.value}",
                                modifier = Modifier
                                    .wrapContentSize()
                                    //.align(Alignment.CenterVertically)
                                    .padding(start = 7.dp),
                                fontSize = 16.sp,
                                color = SplashGreen,
                                //textAlign =
                            )
                        }

                    }


                }

                Divider(color = Color.LightGray, thickness = 0.8.dp)

                Text(
                    text = "Recent Packaging",
                    modifier = Modifier
                        .wrapContentSize()
                        // .align(Alignment.CenterVertically)
                        .padding(start = 17.dp, top = 20.dp),
                    fontSize = 17.sp,
                    color = Color.Gray,
                    //textAlign =
                )
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
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {

                            for ((index, packaging) in viewModel.packagingList.collectAsState().value.withIndex()) {

                                Card(
                                    modifier = Modifier
                                        .animateContentSize(
                                            animationSpec = tween(
                                                durationMillis = 400,
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
                                            Toast
                                                .makeText(
                                                    context,
                                                    "hello$index",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        },
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, Color.LightGray),

                                    ) {


                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .wrapContentSize()
                                    ) {


                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(15.dp)
                                        ) {
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = "${packaging.product_name}",
                                                    modifier = Modifier
                                                        .wrapContentSize(),
                                                    fontSize = 18.sp,
                                                    color = Color.DarkGray,
                                                    //textAlign =
                                                )
                                                Row() {
                                                    Text(
                                                        text = "Batch Number: ",
                                                        modifier = Modifier
                                                            .wrapContentSize(),
                                                        fontSize = 12.sp,
                                                        color = Color.DarkGray,
                                                        //textAlign =
                                                    )
                                                    Text(
                                                        text = "${packaging.batch_no}",
                                                        modifier = Modifier
                                                            .wrapContentSize(),
                                                        fontSize = 12.sp,
                                                        color = Color.DarkGray,
                                                        fontWeight = FontWeight.Bold
                                                        //textAlign =
                                                    )

                                                }

                                                Row() {
                                                    Text(
                                                        text = "Time : ",
                                                        modifier = Modifier
                                                            .wrapContentSize(),
                                                        fontSize = 9.sp,
                                                        color = Color.DarkGray,
                                                        //textAlign =
                                                    )
                                                    Text(
                                                        text = "${packaging.time_stamp}",
                                                        modifier = Modifier
                                                            .wrapContentSize(),
                                                        fontSize = 9.sp,
                                                        color = Color.DarkGray,
                                                        fontWeight = FontWeight.Bold
                                                        //textAlign =
                                                    )

                                                }


                                            }


                                            Image(
                                                painter = painterResource(id = R.drawable.jaya_biscuits),
                                                contentDescription = "biscuitImage",
                                                modifier = Modifier
                                                    .height(65.dp)
                                                    .width(65.dp)
                                                    .align(Alignment.CenterVertically)
                                                    .padding(8.dp)
                                                //.padding(70.dp, 0.dp, 0.dp, 0.dp)
                                            )

                                        }//row

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(40.dp)
                                                .background(AppBarYellow)
                                        ) {

                                            Text(
                                                text = "View Details",
                                                color = Color.DarkGray,
                                                fontSize = 17.sp,
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .weight(1f)
                                                    .padding(start = 15.dp)
                                            )

                                            // Image(painter = painterResource(id = androidx.compose.foundation.layout.R.drawable.ic_baseline_keyboard_backspace_24),
                                            Image(painter = painterResource(id = R.drawable.forward_arrow),
                                                contentDescription = "forward Arrow button",
                                                modifier = Modifier
                                                    .width(55.dp)
                                                    .align(Alignment.CenterVertically)
                                                    .padding(horizontal = 15.dp),
                                                colorFilter = ColorFilter.tint(Color.Black)
                                            )
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

