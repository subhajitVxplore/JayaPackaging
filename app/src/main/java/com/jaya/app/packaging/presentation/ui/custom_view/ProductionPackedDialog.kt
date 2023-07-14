package com.jaya.app.packaging.presentation.ui.custom_view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jaya.app.core.domain.models.AddProductInfoRequest
import com.jaya.app.packaging.presentation.viewModels.AddPackingDetailsViewModel
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.ProductionReportViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductionPackedDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit,
    onButtonClick: () -> Unit,
    viewModel: ProductionReportViewModel,
    baseViewModel: BaseViewModel
) {

    Dialog(onDismissRequest = { setShowDialog(true) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(10.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            textAlign = TextAlign.Center,
                            text = "Add Packed Info",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                // fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }
//---------------------------------------------------------------------------------------------------------------------//
                    Spacer(modifier = Modifier.height(10.dp))


                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            // value = datum.first,
                            value = viewModel.packedGmsText.value,
                            onValueChange = {
                                if (it.length <= 4) viewModel.packedGmsText.value = it
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
                                //.focusRequester(focusRequester)
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
                            // value = datum.first,
                            value = viewModel.packedUnitText.value,
                            onValueChange = {
                                if (it.length <= 4) viewModel.packedUnitText.value = it
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
                                //.focusRequester(focusRequester)
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
                            // value = datum.first,
                            value = viewModel.packedCbText.value,
                            onValueChange = {
                                if (it.length <= 4) viewModel.packedCbText.value = it
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
                                // .focusRequester(focusRequester)
                                .fillMaxWidth()
                                .padding(vertical = 7.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                        )
                    }
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            if (viewModel.packedGmsText.value.isNullOrEmpty()) {
                                Toast.makeText(context,"Please Enter gms. value",Toast.LENGTH_SHORT).show()
                            } else if (viewModel.packedUnitText.value.isNullOrEmpty()) {
                                Toast.makeText(context,"Please Enter unit value",Toast.LENGTH_SHORT).show()
                            } else if (viewModel.packedCbText.value.isNullOrEmpty()) {
                                Toast.makeText(context,"Please Enter cb value",Toast.LENGTH_SHORT).show()
                            } else {
                               // viewModel.packingLabourList.add(viewModel.workersNameTxt.value)
                               // viewModel.packedDetailsList.add("${viewModel.packedGmsText.value}")
                                viewModel.packedDetailsList.add(AddProductInfoRequest("${viewModel.packedGmsText.value}","${viewModel.packedUnitText.value}","${viewModel.packedCbText.value}"))
                                viewModel.totalWeight.value=viewModel.totalWeight.value + (viewModel.packedGmsText.value.toInt() * viewModel.packedUnitText.value.toInt() * viewModel.packedCbText.value.toInt())
                                setShowDialog(false)
                                viewModel.packedGmsText.value = ""
                                viewModel.packedUnitText.value = ""
                                viewModel.packedCbText.value = ""
                            }

                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .wrapContentWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(SplashGreen)
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    }


                }
            }
        }
    }
}

