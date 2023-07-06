package com.jaya.app.packaging.presentation.ui.custom_view

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.viewModels.AddPackingDetailsViewModel
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.presentation.viewModels.DashboardViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoImageDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit,
    onButtonClick: () -> Unit,
    viewModel: DashboardViewModel,
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
                            text = "Add Video/Image Proof",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                // fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }
//-------------------------------------------------------------------------//
                 //   Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                      //  CaptureImage(viewModel,baseViewModel)


                        Column(modifier = Modifier.padding(top = 20.dp)) {
                            val context= LocalContext.current
                            var uploadVideoTxt by remember("Capture Video") { mutableStateOf("Capture Video") }
                            var imageCardBorderColor by remember(Color.Gray) { mutableStateOf(Color.Gray) }
                            Card(
                                border = BorderStroke(2.dp, imageCardBorderColor),
                                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                                modifier = Modifier
                                    .clickable {
                                        viewModel.onUploadVideoToVideoCapture()
                                        viewModel.showVideoImageDialog.value=false
                                    }
                                    .align(Alignment.CenterHorizontally)
                                    .width(90.dp)
                                    .height(90.dp),
                            ) {
                                Box(modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ){

                                    Image(
                                        painterResource(id = R.drawable.baseline_videocam),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp),
                                        colorFilter = ColorFilter.tint(Color.White),
                                        alignment = Alignment.Center
                                    )
                                        imageCardBorderColor= SplashGreen
                                        uploadVideoTxt="Video Captured"

                                }

                            }
                            Text(
                                text = uploadVideoTxt,
                                modifier = Modifier.padding(top = 15.dp),
                                color = Color.DarkGray,
                                style = LocalTextStyle.current.copy(fontSize = 15.sp)
                            )
                        }
                    }


                }
            }
        }
    }
}

