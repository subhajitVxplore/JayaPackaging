package com.jaya.app.packaging.presentation.ui.screen

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.packaging.R
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.presentation.ui.custom_view.LabourSearchDropdown
import com.jaya.app.packaging.presentation.ui.custom_view.PackersNumberDropdown
import com.jaya.app.packaging.presentation.ui.custom_view.PackingMistriDropdown
import com.jaya.app.packaging.presentation.ui.custom_view.PackingOperatorDropdown
import com.jaya.app.packaging.presentation.viewModels.AddPackingDetailsViewModel
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.LogoutRed
import com.jaya.app.packaging.ui.theme.SplashGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPackingDetailsScreen(
    baseViewModel: BaseViewModel,
    viewModel: AddPackingDetailsViewModel = hiltViewModel(),

    ) {

    Surface(modifier = Modifier.fillMaxSize()) {


        val context = LocalContext.current

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
//                                CoroutineScope(Dispatchers.Default).launch {
//                                    viewModel.popScreen()
//                                }
                                viewModel.onBack()
                            },
                            role = Role.Image
                        )
                )

                Text(
                    text = "Add Packing Details",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.DarkGray,
                    style = LocalTextStyle.current.copy(fontSize = 20.sp)
                )

            }
//----------------------------------------------------------------------------------
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 85.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(10.dp))

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
                        ),
//                        .clickable {
//                            //Toast.makeText(context,"hello$index",Toast.LENGTH_SHORT).show()
//                            viewModel.onHomePageToAddPackingDetails()
//                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .height(160.dp)
                    ) {

                        Column(modifier = Modifier.weight(1f)) {

                            Row(
                                modifier = Modifier
                                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
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

                        }

                    }//column

                }

                OutlinedTextField(
                    readOnly = true,
                    enabled = false,
                    value = baseViewModel.packingSupervisor.value,
                    onValueChange = { viewModel.packingSupervisorName.value = it },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Packing Supervisor Name", color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    singleLine = true
                )

                PackingMistriDropdown(
                    viewModel,
                    false,
                    // listOf("Mistri A","Mistri B","Mistri C","Mistri D","Mistri E"),
                    viewModel.packingMistriList.collectAsState().value,
                    onSelect = {
                        //  Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        //viewModel.selectedPincode.value = it
                    })

                PackingOperatorDropdown(
                    viewModel,
                    false,
                    //listOf("Operator A","Operator B","Operator C","Operator D","Operator E"),
                    viewModel.packingOperatorList.collectAsState().value,
                    onSelect = {
                        //  Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        //viewModel.selectedPincode.value = it
                    })

                PackersNumberDropdown(
                    viewModel,
                    false,
                    // listOf("Packers 1","Packers 2","Packers 3","Packers 4","Packers 5"),
                    viewModel.packersNumberList.collectAsState().value,
                    onSelect = {
                        //  Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        //viewModel.selectedPincode.value = it
                    })

                LabourSearchDropdown(
                    viewModel,
                    false,
                    // listOf("hello","Subhajit","Sunil","Samaresh"),
                    viewModel.packingLabours.collectAsState().value,
                    onSelect = {
                        //  Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        //viewModel.selectedPincode.value = it
                    })

                for ((index, workersName) in viewModel.packingLabourList.withIndex()) {
//                    var value by remember(workersName) {
//                        mutableStateOf(workersName)
//                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = "${index + 1}. $workersName",
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically),
                            //   .wrapContentSize(),
                            fontSize = 17.sp,
                            color = Color.DarkGray,
                            // fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = {
                            //  viewModel.showHidepasswordText.value = false
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_labour_svg),
                                contentDescription = null,
                                tint = LogoutRed,
                                modifier = Modifier
                                    .width(screenWidth * 0.15f)
                                    .align(Alignment.CenterVertically)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                        onClick = {
                                            viewModel.packingLabourList.removeAt(index)
                                        },
                                        role = Role.Image
                                    )
                            )
                        }

                    }
                }


            }//column


        }//parentColumn
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            val context = LocalContext.current
            Button(
                onClick = {

                          viewModel.addPackingDetails()
                    baseViewModel.isHomePageShow.value = true
                },
                enabled = viewModel.loadingButton.value,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 18.dp)
                    .fillMaxWidth()
                    .height(53.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.addBtnDeepGreenColor))
            ) {
                if (!viewModel.loadingg.value) {
                    Text(
                        text = "Confirm Now",
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                } else {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }

//        LaunchedEffect(startHour,endHour,startMinute,endMinute){
//            if ((startHour>=endHour)&&(startMinute>endMinute)){
//                viewModel.endTimeSelected.value
//            }
//        }

    }//surface

    viewModel.saveDetailsDialog.value?.apply {
        if (currentState()) {
            AlertDialog(
                containerColor = AppBarYellow,
                shape = RoundedCornerShape(10.dp),
                onDismissRequest = {
                    onDismiss?.invoke(null)
                },
                title = {
                    currentData?.title?.let {
                        Text(text = it)
                    }
                },
                text = {
                    currentData?.message?.let {
                        Text(text = it) //version message from Api
                    }
                },
                dismissButton = {
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

                },
                confirmButton = {
                    val context = LocalContext.current
                    currentData?.positive?.let {
                        Button(
                            onClick = {
                                viewModel.loadingg.value = true

                                // viewModel.addProduct()

                                val timer = object : CountDownTimer(5000, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        // Toast.makeText(context, "${viewModel.timerX.value}", Toast.LENGTH_SHORT).show()
                                    }

                                    override fun onFinish() {
                                        Toast.makeText(
                                            context,
                                            "${viewModel.backendMessage.value}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                timer.start()
                                onDismiss?.invoke(null)
                            },
                            colors = ButtonDefaults.buttonColors(SplashGreen),
                            shape = RoundedCornerShape(7.dp),
                        ) {
                            Text(text = it)
                        }
                    }
                },
                properties = DialogProperties(
                    dismissOnClickOutside = false
                )

            )
        }
    }

}//AddProductScreen