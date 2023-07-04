@file:Suppress("DEPRECATION")

package com.jaya.app.packaging.presentation.ui.screen

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.packaging.R
import com.jaya.app.packaging.extensions.bottomToUp
import com.jaya.app.packaging.extensions.screenHeight
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.extensions.upToBottom
import com.jaya.app.packaging.presentation.ui.custom_view.PlantDropdown
import com.jaya.app.packaging.presentation.ui.custom_view.ShiftDropdown
import com.jaya.app.packaging.presentation.viewModels.DashboardViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.SplashGreen
import io.ktor.util.reflect.*

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun ShiftPlantSelectionPage(
    openDrawer: () -> Unit,
    viewModel: DashboardViewModel
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.weight(1f),
        ) { padding ->

            Column(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            viewModel.isShiftAselected.value = true
                            viewModel.shiftABtnBackColor.value= SplashGreen
                            viewModel.shiftATxtColor.value= Color.White

                            viewModel.isShiftBselected.value = false
                            viewModel.shiftBBtnBackColor.value= Color.White
                            viewModel.shiftBTxtColor.value= Color.DarkGray

                            viewModel.isShiftCselected.value = false
                            viewModel.shiftCBtnBackColor.value= Color.White
                            viewModel.shiftCTxtColor.value= Color.DarkGray
                        },
                        colors = ButtonDefaults.buttonColors(viewModel.shiftABtnBackColor.value),
                        border = BorderStroke(0.5.dp, Color.LightGray),
                        elevation = ButtonDefaults.buttonElevation(20.dp)

                    ) {
                        Text(
                            text = "Shift A",
                            color = viewModel.shiftATxtColor.value,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            viewModel.isShiftBselected.value = true
                            viewModel.shiftBBtnBackColor.value= SplashGreen
                            viewModel.shiftBTxtColor.value= Color.White

                            viewModel.isShiftAselected.value = false
                            viewModel.shiftABtnBackColor.value= Color.White
                            viewModel.shiftATxtColor.value= Color.DarkGray

                            viewModel.isShiftCselected.value = false
                            viewModel.shiftCBtnBackColor.value= Color.White
                            viewModel.shiftCTxtColor.value= Color.DarkGray
                        },
                        colors = ButtonDefaults.buttonColors(viewModel.shiftBBtnBackColor.value),
                        border = BorderStroke(0.5.dp, Color.LightGray),
                        elevation = ButtonDefaults.buttonElevation(20.dp)

                    ) {
                        Text(
                            text = "Shift B",
                            color = viewModel.shiftBTxtColor.value,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            viewModel.isShiftCselected.value = true
                            viewModel.shiftCBtnBackColor.value= SplashGreen
                            viewModel.shiftCTxtColor.value= Color.White

                            viewModel.isShiftBselected.value = false
                            viewModel.shiftBBtnBackColor.value= Color.White
                            viewModel.shiftBTxtColor.value= Color.DarkGray

                            viewModel.isShiftAselected.value = false
                            viewModel.shiftABtnBackColor.value= Color.White
                            viewModel.shiftATxtColor.value= Color.DarkGray
                        },
                        colors = ButtonDefaults.buttonColors(viewModel.shiftCBtnBackColor.value),
                        border = BorderStroke(0.5.dp, Color.LightGray),
                        elevation = ButtonDefaults.buttonElevation(20.dp)

                    ) {
                        Text(
                            text = "Shift C",
                            color = viewModel.shiftCTxtColor.value,
                            fontSize = 15.sp
                        )
                    }


                }

                Card(
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.padding(horizontal = 15.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(60.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .background(AppBarYellow)
                                .weight(1f)
                                .fillMaxHeight(),
                        ) {
                            Text(
                                text = "Plant",
                                color = Color.DarkGray,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(20.dp),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier

                                .weight(1f)
                                .fillMaxHeight(),
                        ) {
                            PlantDropdown(
                                //viewModel,
                                false,
                                listOf("Plant1", "Plant2", "Plant3", "Plant4"),
                                onSelect = {
                                    //baseViewModel.getStartedSelectedPlant.value=it
                                    // Toast.makeText(context, "${baseViewModel.getStartedSelectedPlant.value}", Toast.LENGTH_SHORT).show()
                                    //viewModel.selectedPincode.value = it
                                })
                        }
                    }//row
                }//card


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
                            modifier = Modifier.padding(top = 15.dp)
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
                                            //Toast.makeText(context,"hello$index",Toast.LENGTH_SHORT).show()
                                            viewModel.onHomePageToAddPackingDetails()
                                        },
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

                                                Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
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

                                                Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
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
                                                Row(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.DarkGray)) {
                                                    Text(
                                                        text = "Dream Marie",
                                                        fontSize = 16.sp,
                                                        color = Color.White,
                                                        modifier = Modifier.align(Alignment.CenterVertically).padding(start = 20.dp)
                                                    )
                                                }

                                                Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                                                    Text(
                                                        text = "Mixing Supervisor : ",
                                                        modifier = Modifier
                                                            .wrapContentSize(),
                                                        fontSize = 16.sp,
                                                        color = Color.DarkGray,
                                                        //textAlign =
                                                    )
                                                    Text(
                                                        text = "-",
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


