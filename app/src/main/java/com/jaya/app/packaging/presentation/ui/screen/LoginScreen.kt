package com.jaya.app.packaging.presentation.ui.screen

import android.text.TextUtils
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    baseViewModel: BaseViewModel,
    viewModel: LoginViewModel = hiltViewModel()
) {
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

                OutlinedTextField(
                    value = viewModel.emailText.value,
                    onValueChange = { viewModel.emailText.value = it },
                    //label = { Text("your mobile number") },
                    placeholder = { Text("Enter your email Id") },
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
                        keyboardType = KeyboardType.Email
                    ),
                )


val context= LocalContext.current
 //----------------------------------------------------------------------------------------------------------------
                fun String.isValidEmail(): Boolean {
                    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
                        .matches()
                }
//----------------------------------------------------------------------------------------------------------------

                Button(
                    onClick = {
                     //   Toast.makeText(context, "continue", Toast.LENGTH_SHORT).show()
                        //viewModel.loader.value=true

                        if (!viewModel.emailText.value.isValidEmail()){
                            Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                        }else{
                            baseViewModel.storedLoginEmail.value=viewModel.emailText.value
                            viewModel.onLoginToOtp()
                        }


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
                        text = "Get OTP Verification",
                        color = Color.White,
                        fontSize = 20.sp,
                    )

                }

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
 //------------------------------------------------------------------------------

}//LoginScreen

//=====================================================================================



fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}