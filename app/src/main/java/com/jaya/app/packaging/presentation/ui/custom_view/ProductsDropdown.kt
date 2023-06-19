package com.jaya.app.packaging.presentation.ui.custom_view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.packaging.presentation.extensions.bottomToUp
import com.jaya.app.packaging.presentation.extensions.screenHeight
import com.jaya.app.packaging.presentation.extensions.screenWidth
import com.jaya.app.packaging.presentation.extensions.upToBottom
import com.jaya.app.packaging.presentation.viewModels.AddProductViewModel
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductsDropdown(
    viewModel: AddProductViewModel,
    loading: Boolean,
    //dataList: List<ExpenseTypes>,
    dataList: List<String>,
    onSelect: (String) -> Unit
) {//need to inherit "DropDownItem" to model classes

    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }

    //  mSelectedText=baseViewModel.prefilledExpenseType
    if (mSelectedText.isEmpty()) {
        mSelectedText = viewModel.selectedProduct.value
    }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    AnimatedContent(
        targetState = loading,
        transitionSpec = {
            if (targetState && !initialState) {
                upToBottom()
            } else {
                bottomToUp()
            }
        }
    ) {
        if (!it) {

            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)) {
                Surface(
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(4.dp),
                    //backgroundColor = Color.Yellow
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {

                        Row(modifier = Modifier
                            .clickable { mExpanded = !mExpanded }
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(start = 15.dp)) {
                            Text(
                                text = mSelectedText,
                                //label = label,
                                color = Color.DarkGray,
                                fontSize = 17.sp,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        Icon(icon, "contentDescription",
                            Modifier
                                .padding(end = 4.dp)
                                .align(Alignment.CenterVertically)
                                .clickable { mExpanded = !mExpanded })

                    }
                }

//------------------------------------------------------------------------------------//


                DropdownMenu(
                    modifier= Modifier.wrapContentHeight(),
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                ) {
                    dataList.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(text = item)
                                    Divider(
                                        color = Color.LightGray,
                                        thickness = 0.8.dp,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                }
                                   },
                            onClick = {
                            mSelectedText = item
                            mExpanded = false
                            onSelect(mSelectedText)
                        })

                    }
                }//DropdownMenu





            }

        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(width = screenWidth * 0.15f, height = screenHeight * 0.15f)
                    .padding(bottom = screenHeight * 0.05f),
                color = Color.Black,
                strokeWidth = 5.dp,
            )
        }
    }

}

/////////////////////////////////////////////////////////////////

//interface DropDownItem {
//    val name: String
//}