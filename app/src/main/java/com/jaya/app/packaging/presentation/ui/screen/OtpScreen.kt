package com.jaya.app.packaging.presentation.ui.screen

import android.os.CountDownTimer
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.ui.custom_view.OtpTextField
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.OtpViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    baseViewModel: BaseViewModel,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val focusRequester = remember { FocusRequester() }

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
                //-------------------------------------------------------------------------------------------------
                fun String.isValidEmail(): Boolean {
                    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(
                        this
                    )
                        .matches()
                }
//-------------------------------------------------------------------------------------------------
                OutlinedTextField(
                    value = baseViewModel.storedLoginEmail.value,
                    onValueChange = {
                        baseViewModel.storedLoginEmail.value = it
                        viewModel.color.value = Color.Gray
                        if (baseViewModel.storedLoginEmail.value.isValidEmail())
                            viewModel.color.value = SplashGreen
                    },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Enter your email Id") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = viewModel.color.value,
                        unfocusedBorderColor = Color.Gray
                    ),
                    readOnly = viewModel.isEmailReadOnly.value,
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Edit,
                            "contentDescription",
                            Modifier
                                .padding(end = 4.dp)

                                .clickable {
                                    viewModel.isEmailReadOnly.value =
                                        !viewModel.isEmailReadOnly.value

                                    if (viewModel.isEmailReadOnly.value) viewModel.color.value =
                                        Color.Gray else viewModel.color.value = Color.Blue

                                }
                        )

                    },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    ),
                )


                OtpTextField(
                    otpText = viewModel.otpNumber.value,
                    onOtpTextChange = { value, otpInputFilled ->
                        viewModel.otpNumber.value = value
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp, start = 10.dp)
                )

                val context = LocalContext.current

                Button(
                    onClick = {

                        if (!baseViewModel.storedLoginEmail.value.isValidEmail()) {
                            viewModel.color.value = Color.Red
                            focusRequester.requestFocus()
                            Toast.makeText(
                                context,
                                "Please enter a valid email",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (viewModel.otpNumber.value.length < 4) {
                            Toast.makeText(context, "Enter 4 digit OTP", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.loadingg.value = true
                            viewModel.verifyOtp()
                            val timer = object : CountDownTimer(5000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    // Toast.makeText(context, "${viewModel.timerX.value}", Toast.LENGTH_SHORT).show()
                                }

                                override fun onFinish() {
                                    Toast.makeText(
                                        context,
                                        "${viewModel.successMessage.value}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            timer.start()
                        }


                    },
                    enabled = viewModel.loadingButton.value,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 25.dp)
                        .fillMaxWidth()
                        .height(53.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    if (!viewModel.loadingg.value) {
                        Text(
                            text = "Verify Now",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    } else {
                        CircularProgressIndicator(color = Color.White)
                    }

                }

                Text(
                    text =viewModel.resendButtonTxt.value,
                    //text = "Resend OTP.",
                    modifier = Modifier
                        .clickable {
                            viewModel.resendOtp()
                            val timer = object : CountDownTimer(15000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    viewModel.resendButtonTxt.value ="00:0${(millisUntilFinished / 1000)}"
                                }
                                override fun onFinish() {
                                    viewModel.resendButtonTxt.value = "Resend OTP."
                                    viewModel.loadingButton.value=true
                                }
                            }
                            timer.start()

                            val innerTimer = object : CountDownTimer(5000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    // Toast.makeText(context, "${viewModel.timerX.value}", Toast.LENGTH_SHORT).show()
                                }
                                override fun onFinish() {
                                    Toast.makeText(context, "${viewModel.successMessage.value}", Toast.LENGTH_SHORT).show()
                                }
                            }
                            innerTimer.start()

                        }
                        .padding(bottom = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,
                    color = Color.Gray,
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )


            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "All Rights Reserved by Jaya Industries Pvt Ltd",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.Gray
                )
            }
        }
    }

    LaunchedEffect(true) { viewModel.isEmailReadOnly.value }

}//OtpScreen
