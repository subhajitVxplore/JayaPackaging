package com.jaya.app.packaging.presentation.ui.screen

import android.app.TimePickerDialog
import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.extensions.screenWidth
import com.jaya.app.packaging.presentation.ui.custom_view.ProductsDropdown
import com.jaya.app.packaging.presentation.viewModels.AddProductViewModel
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.LogoutRed
import com.jaya.app.packaging.ui.theme.SplashGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    baseViewModel: BaseViewModel,
    viewModel: AddProductViewModel = hiltViewModel(),

    ) {

    Surface(modifier = Modifier.fillMaxSize()) {


        val context = LocalContext.current
//=====================================================================================
        val startContext = LocalContext.current
        val startCalendar = Calendar.getInstance()
        val startHour = startCalendar[Calendar.HOUR_OF_DAY]
        val startMinute = startCalendar[Calendar.MINUTE]
        val startTimePickerDialog = TimePickerDialog(
            startContext,
            { _, startHour: Int, startMinute: Int ->
                viewModel.startTimeSelected.value = "$startHour:$startMinute"
            }, startHour, startMinute, false
        )
//====================================================================================

        val endContext = LocalContext.current
        val endCalendar = Calendar.getInstance()
        val endHour = endCalendar[Calendar.HOUR_OF_DAY]
        val endMinute = endCalendar[Calendar.MINUTE]
        val endTimePickerDialog = TimePickerDialog(
            endContext,
            { _, endHour: Int, endMinute: Int ->
                // if ((startHour>=endHour)&&(startMinute>endMinute)) {
                if ((endHour >= startHour) && (endMinute > startMinute)) {
                    viewModel.endTimeSelected.value = "$endHour:$endMinute"
                } else {
                    Toast.makeText(context, "Invalid End Time", Toast.LENGTH_SHORT).show()
                }
            }, endHour, endMinute, false
        )
//===================================================================================


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
                                CoroutineScope(Dispatchers.Default).launch {
                                    viewModel.popScreen()
                                }
                            },
                            role = Role.Image
                        )
                )

                Text(
                    text = "Add Product",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.DarkGray,
                    style = LocalTextStyle.current.copy(fontSize = 20.sp)
                )

            }
//----------------------------------------------------------------------------------
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = viewModel.productName.value,
                    onValueChange = { viewModel.productName.value = it },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Product Name") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.packingName.value,
                    onValueChange = { viewModel.packingName.value = it },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Packing Name") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.batchNumber.value,
                    onValueChange = { viewModel.batchNumber.value = it },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Batch Number") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    singleLine = true
                )


                val context = LocalContext.current

                ProductsDropdown(
                    viewModel,
                    false,
                    viewModel.productTypes.collectAsState().value,
                    onSelect = {
                        //  Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        //viewModel.selectedPincode.value = it
                    })



                Row(
                    modifier = Modifier
                        .padding(start = 7.dp, end = 3.dp, top = 10.dp)
                        .wrapContentSize()
                    // .align(Alignment.CenterHorizontally)
                ) {

                    Card(
                        border = BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize(),
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                        ) {

                            Text(
                                text = "${viewModel.startTimeSelected.value}",
                                color = Color.DarkGray,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(15.dp)
                            )

                            Image(painter = painterResource(R.drawable.outline_watch_round),
                                contentDescription = "calender button",
                                colorFilter = ColorFilter.tint(Color.DarkGray),
                                modifier = Modifier
                                    .height(30.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable {
                                        startTimePickerDialog.show()
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(horizontal = 10.dp)
                            )

                        }//row
                    }//card

                    Spacer(modifier = Modifier.width(width = 10.dp))
                    Card(
                        border = BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize(),
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                        ) {

                            //      if ((startHour>=endHour)&&(startMinute>endMinute)){
                            //       if (startHour>=endHour){
                            Text(
                                text = "${viewModel.endTimeSelected.value}",
                                color = Color.DarkGray,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(15.dp)
                            )
                            //     }


                            Image(painter = painterResource(R.drawable.outline_watch_round),
                                contentDescription = "time button",
                                colorFilter = ColorFilter.tint(Color.DarkGray),
                                modifier = Modifier
                                    .height(30.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable {
                                        // if ((startHour > endHour) && (startMinute > endMinute)) {
                                        if (viewModel.startTimeSelected.value == "Start Time") {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Select start time first",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        } else {
                                            endTimePickerDialog.show()
                                        }

                                        //}

                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(horizontal = 10.dp)
                            )

                        }//row
                    }//card


                }//parentrow


                Row(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 20.dp, top = 15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Video Clip",
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
//                            .clickable {
//                                viewModel.onUploadVideoToVideoCapture()
//                            }
                    )

                    Button(
                        onClick = {
                            viewModel.onUploadVideoToVideoCapture()
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.addBtnGreenColor)),
                        modifier = Modifier.wrapContentSize(),
                        //enabled = true,
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(
                            text = "Add",
                            color = Color.White,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Icon(Icons.Filled.Add, "add")
                    }

                }//row


                for ((index, videoClips) in baseViewModel.videoUriList.withIndex()) {

                    Card(
                        border = BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.whitishGray)),
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                                .wrapContentSize()
                        ) {

                            Card(
                                border = BorderStroke(1.dp, Color.Gray),
                                shape = RoundedCornerShape(5.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .wrapContentSize(),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .wrapContentSize()
                                ) {

                                    Text(
                                        //text = "${baseViewModel.videoShootTime}",
                                        text = baseViewModel.videoShootTime[index].toString(),
                                        color = Color.DarkGray,
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(15.dp)
                                    )

                                    Image(
                                        painter = painterResource(R.drawable.outline_watch_round),
                                        colorFilter = ColorFilter.tint(Color.Gray),
                                        contentDescription = "time button",
                                        modifier = Modifier
                                            .height(30.dp)
                                            .align(Alignment.CenterVertically)
                                            .padding(horizontal = 10.dp)
                                    )


                                }//row
                            }//card

                            Spacer(modifier = Modifier.width(width = 10.dp))
                            Card(
                                border = BorderStroke(1.dp, Color.Gray),
                                shape = RoundedCornerShape(5.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentSize(),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .wrapContentSize()
                                ) {

                                    Text(
                                        text = "Upload Video",
                                        color = Color.DarkGray,
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(15.dp)
                                    )

                                    Image(
                                        painter = painterResource(R.drawable.outline_file_upload_24),
                                        contentDescription = "time button",
                                        // colorFilter = ColorFilter.tint(colorResource(R.color.app_green)),
                                        modifier = Modifier
                                            .height(30.dp)
                                            .align(Alignment.CenterVertically)
                                            .padding(horizontal = 10.dp)
                                    )

                                }//row
                            }//card


                        }//parentrow

                    }
                    //    }//if (index > 0)


                }//for

            }//column


        }//parentColumn
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            val context = LocalContext.current
            Button(
                onClick = {

                    if (viewModel.productName.value.isNullOrEmpty()) {
                        Toast.makeText(context, "Product name can not be empty", Toast.LENGTH_SHORT)
                            .show()
                    } else if (viewModel.packingName.value.isNullOrEmpty()) {
                        Toast.makeText(context, "Packing name can not be empty", Toast.LENGTH_SHORT)
                            .show()
                    } else if (viewModel.batchNumber.value.isNullOrEmpty()) {
                        Toast.makeText(context, "Batch Number can not be empty", Toast.LENGTH_SHORT)
                            .show()
                    } else if (viewModel.selectedProduct.value == "Choose Product") {
                        Toast.makeText(context, "Please choose a product", Toast.LENGTH_SHORT)
                            .show()
                    } else if (viewModel.startTimeSelected.value == "Start Time") {
                        Toast.makeText(context, "Please select start time", Toast.LENGTH_SHORT)
                            .show()
                    } else if (viewModel.endTimeSelected.value == "End Time") {
                        Toast.makeText(context, "Please select end time", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.onSaveProductDialog()
                    }

//----------------------------------------------------------------------------------------------------------------

//                    if (baseViewModel.videoUri != null) {
//                        val file = File(baseViewModel.videoUri?.path)
//                        Toast.makeText(context, "${file.name}", Toast.LENGTH_SHORT).show()
//                    }else{
//                        Toast.makeText(context, "null-uri", Toast.LENGTH_SHORT).show()
//                    }


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
                        text = "Save",
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
                    val context = LocalContext.current
                    currentData?.positive?.let {
                        Button(
                            onClick = {
                                viewModel.loadingg.value = true
                                viewModel.addProduct()
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