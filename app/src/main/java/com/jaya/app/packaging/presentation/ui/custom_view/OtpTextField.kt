package com.jaya.app.packaging.presentation.ui.custom_view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it, it.length == otpCount)
            }
        },

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(index = index,text = otpText)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "I"
        index > text.length -> ""
        else -> text[index].toString()
    }

    Text(

        modifier = Modifier
            .width(60.dp).height(60.dp)
            .border(
                1.dp, when {
                    isFocused -> Color.Gray
                    else -> Color.LightGray
                }, RoundedCornerShape(5.dp)
            )
            .padding(10.dp),
        text = char,
        fontSize = 25.sp,
        color = if (isFocused) {
            Color.LightGray
        } else {
            Color.Gray
        },
        textAlign = TextAlign.Center
    )
}
