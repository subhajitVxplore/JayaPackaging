package com.jaya.app.packaging.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.packaging.R
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.presentation.ui.custom_view.BatchDropdown
import com.jaya.app.packaging.presentation.ui.custom_view.ProductionPackedDialog
import com.jaya.app.packaging.presentation.ui.custom_view.WorkersNameDialog
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.ProductionReportViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.SplashGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductionReportScreen(
    baseViewModel: BaseViewModel,
    viewModel: ProductionReportViewModel = hiltViewModel()
) {

    val focusRequester = remember { FocusRequester() }

    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        if (viewModel.showProductionPackedDialog.value)
            ProductionPackedDialog(
                value = "", setShowDialog = {
                    viewModel.showProductionPackedDialog.value = it
                },
                onButtonClick = {
                    //  viewModel.checkIfDeviceFound()
                },
                viewModel,
                baseViewModel
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
//                                CoroutineScope(Dispatchers.Default).launch {
//                                    viewModel.popScreen()
//                                }
                                viewModel.onBack()
                            },
                            role = Role.Image
                        )
                )

                Text(
                    text = "Production Report",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.DarkGray,
                    style = LocalTextStyle.current.copy(fontSize = 20.sp)
                )

            }
//----------------------------------------------------------------------------------
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(25.dp))

                Card(
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
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
                            Text(
                                text = "Plant 5 (Shift A)",
                                color = Color.DarkGray,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 15.dp),
                                textAlign = TextAlign.Center,
                                //fontWeight = FontWeight.Bold
                            )
                        }
                    }//row
                }//card

                Card(
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(top = 15.dp)
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
                                text = "Batches",
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
                            BatchDropdown(
                                //viewModel,
                                false,
                                listOf("12", "13", "14", "15"),
                                onSelect = {
                                    viewModel.batchName.value=it
                                    //  Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                    //viewModel.selectedPincode.value = it
                                })
                        }
                    }//row
                }//card

                Card(
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(top = 15.dp)
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
                                text = "Product",
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
                            Text(
                                text = "Dream Marie",
                                color = Color.DarkGray,
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 15.dp),
                                textAlign = TextAlign.Center,
                                //fontWeight = FontWeight.Bold
                            )
                        }
                    }//row
                }//card

                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "We packed",
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )

                    Button(
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            //viewModel.packedDetailsList.add("")
                            viewModel.showProductionPackedDialog.value = true

                        },
                        colors = ButtonDefaults.buttonColors(SplashGreen),
                        modifier = Modifier
                            .wrapContentSize(),
                        //.padding(end = 10.dp),
                        //enabled = true,
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Icon(
                            Icons.Default.Add,
                            tint = Color.White,
                            contentDescription = "",
                            modifier = Modifier.padding(start = 10.dp, end = 5.dp)
                        )
                        Text(
                            text = "Add More",
                            color = Color.White,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }

                }//row

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Gms",
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Unit",
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "CB",
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )


                }
                for ((index, packedDetails) in viewModel.packedDetailsList.withIndex()) {

                    var gmsValue by remember(packedDetails) {mutableStateOf(packedDetails)}
                    var unitValue by remember(packedDetails) {mutableStateOf(packedDetails)}
                    var cbValue by remember(packedDetails) {mutableStateOf(packedDetails)}


                    // val datum = dataList[index]
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            readOnly = true,
                            value = gmsValue.gms_value,
                            onValueChange = {
                                if (it.length <= 4) gmsValue.gms_value = it
                                //dataList[index] = dataList[index].copy(first = it)
                            },
                            //label = { Text("your mobile number") },
                           // placeholder = { Text("Person ${index + 1}") },
                            placeholder = { Text("gms") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.LightGray,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester)
                                .fillMaxWidth()
                                .padding(vertical = 7.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        OutlinedTextField(
                            readOnly = true,
                            value = unitValue.unit_value,
                            onValueChange = {
                                if (it.length <= 4) unitValue.unit_value = it
                                //dataList[index] = dataList[index].copy(first = it)
                            },
                            //label = { Text("your mobile number") },
                            placeholder = { Text("unit") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.LightGray,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester)
                                .fillMaxWidth()
                                .padding(vertical = 7.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        OutlinedTextField(
                            readOnly = true,
                            value = cbValue.cb_value,
                            onValueChange = {
                                if (it.length <= 4) cbValue.cb_value = it
                                //dataList[index] = dataList[index].copy(first = it)
                            },
                            //label = { Text("your mobile number") },
                            placeholder = { Text("cb") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.LightGray,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester)
                                .fillMaxWidth()
                                .padding(vertical = 7.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                        )
                    }

                }//for


               // CaptureImage(baseViewModel)

                Text(
                    text = "Total Weight = 3.76 Tons",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Gray,
                    style = LocalTextStyle.current.copy(fontSize = 20.sp)
                )


            }//parentColumn

        }
        //-------------------------------------------------------------------------------------------------------
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            val context = LocalContext.current
            Button(
                onClick = {

//                    if (viewModel.batchName.value.isNullOrEmpty()){
//                        Toast.makeText(context, "Please select a batch", Toast.LENGTH_SHORT).show()
//                    }else if (viewModel.productName.value.isNullOrEmpty()){
//                        Toast.makeText(context, "Please select a product", Toast.LENGTH_SHORT).show()
//                    }else if (viewModel.packedDetailsList.isNullOrEmpty()){
//                        Toast.makeText(context, "Please add packing details", Toast.LENGTH_SHORT).show()
//                    }else{
//                        baseViewModel.isProductionReportDone.value = true
//                        CoroutineScope(Dispatchers.Default).launch {viewModel.popScreen()}
//                    }


                },
                enabled = viewModel.loadingButton.value,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 18.dp)
                    .fillMaxWidth()
                    .height(53.dp),
                colors = ButtonDefaults.buttonColors(SplashGreen)
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
    }//surface
    //------------------------------------------------------------------------------

}//LoginScreen

//=====================================================================================


