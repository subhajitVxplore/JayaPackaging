package com.jaya.app.packaging.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.ui.custom_view.OtpTextField
import com.jaya.app.packaging.presentation.viewModels.LoginViewModel
import com.jaya.app.packaging.presentation.viewModels.OtpViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    navController : NavController,
    viewModel: OtpViewModel = hiltViewModel()
) {

    var otpValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.wrapContentSize()) {
                Image(
                    painter = painterResource(R.drawable.cropped_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(180.dp)
                        .height(130.dp),
                )

                Text(
                    text = "Welcome to Jaya Industries",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Please Verify your OTP",
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )

                OutlinedTextField(
                    value = viewModel.mobile.value,
                    onValueChange = { viewModel.mobile.value = it },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Email or mobile number") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                )


                OtpTextField(
                    otpText = otpValue,
                    onOtpTextChange = { value, otpInputFilled ->
                        otpValue = value
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp, start = 10.dp)
                )

val context= LocalContext.current
                Button(
                    onClick = {
                        Toast.makeText(context, "Verify Now", Toast.LENGTH_SHORT).show()
                        //viewModel.loader.value=true
                       // viewModel.onLoginToOtp()

                    },
                    //enabled = true,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 25.dp)
                        .fillMaxWidth()
                        .height(53.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
//                    if (!viewModel.loader.value){
//                        Text(
//                            text = "Continue",
//                            color = Color.White,
//                            fontSize = 20.sp,
//                        )
//                    }else{
//                        CircularProgressIndicator(color = Color.White)
//                    }
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
                Text(
                    text = "Resend OTP.",
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,
                    color = Color.Gray,
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )


            }
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ){
                Text(
                    text = "All Rights Reserved by Jaya Industries Pvt Ltd",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.Gray
                )
            }



        }


    }

}