package com.jaya.app.packaging.presentation.ui.screen

import android.os.CountDownTimer
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.LoginViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    baseViewModel: BaseViewModel,
    viewModel: LoginViewModel = hiltViewModel()
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
                    text = "Login Here",
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )

                //----------------------------------------------------------------------------------------------------------------
                fun String.isValidEmail(): Boolean {
                    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(
                        this
                    )
                        .matches()
                }
//----------------------------------------------------------------------------------------------------------------

                OutlinedTextField(
                    value = viewModel.emailText.value,
                    // onValueChange = { viewModel.emailText.value = it },
                    onValueChange = {
                        viewModel.emailText.value = it
                        viewModel.emailFieldcolor.value = Color.Gray
                        if (viewModel.emailText.value.isValidEmail())
                            viewModel.emailFieldcolor.value = SplashGreen
                    },
                    //label = { Text("your mobile number") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "",
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            tint = Color.LightGray
                        )
                    },
                    placeholder = { Text("Enter your email Id", color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = viewModel.emailFieldcolor.value,
                        unfocusedBorderColor = Color.Gray
                    ),
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
                OutlinedTextField(
                    value = viewModel.passwordText.value,
                    // onValueChange = { viewModel.passwordText.value = it },
                    onValueChange = {
                        if (it.length<20) viewModel.passwordText.value = it
                        viewModel.passwordFieldcolor.value = Color.Gray
                        if (!viewModel.passwordText.value.isNullOrEmpty())
                            viewModel.passwordFieldcolor.value = SplashGreen
                    },
                    //label = { Text("your mobile number") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.password_lock_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            tint = Color.LightGray
                        )
                    },
                    placeholder = { Text("Enter Password", color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = viewModel.passwordFieldcolor.value,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    singleLine = true,
                    visualTransformation = if (viewModel.showHidepasswordText.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        if (!viewModel.passwordText.value.isNullOrEmpty())
                        if (viewModel.showHidepasswordText.value) {
                            IconButton(onClick = { viewModel.showHidepasswordText.value = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide_password"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { viewModel.showHidepasswordText.value = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "show_password"
                                )
                            }
                        }
                    }
                )

//LaunchedEffect(true ){viewModel.emailText.value}

                val context = LocalContext.current

                Button(
                    onClick = {
                        //   Toast.makeText(context, "continue", Toast.LENGTH_SHORT).show()
                        //viewModel.loader.value=true

                        if (!viewModel.emailText.value.isValidEmail()) {
                            viewModel.emailFieldcolor.value = Color.Red
                            focusRequester.requestFocus()
                            Toast.makeText(
                                context,
                                "Please enter a valid email !!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else if (viewModel.passwordText.value.isNullOrEmpty()){
                            viewModel.passwordFieldcolor.value = Color.Red
                            focusRequester.requestFocus()
                            Toast.makeText(
                                context,
                                "Please enter valid password !!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else {
                            viewModel.loadingg.value = true
                            baseViewModel.storedLoginEmail.value = viewModel.emailText.value
                            //viewModel.getOtp()
                            viewModel.login()

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
                            text = "Get OTP Verification",
                            color = Color.White,
                            fontSize = 18.sp,
                        )
                    } else {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
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
    //------------------------------------------------------------------------------

}//LoginScreen

//=====================================================================================


fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}