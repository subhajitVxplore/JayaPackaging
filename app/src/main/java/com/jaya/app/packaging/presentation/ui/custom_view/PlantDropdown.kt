package com.jaya.app.packaging.presentation.ui.custom_view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.core.domain.models.Plant
import com.jaya.app.packaging.extensions.bottomToUp
import com.jaya.app.packaging.extensions.screenHeight
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.extensions.upToBottom


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlantDropdown(
    // viewModel: AddProductViewModel,
    loading: Boolean,
    dataList: List<Plant>,
    onSelect: (String) -> Unit
) {//need to inherit "DropDownItem" to model classes

    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }

    //  mSelectedText=baseViewModel.prefilledExpenseType
    if (mSelectedText.isEmpty()) {
       // mSelectedText = "Select Plant"
        mSelectedText = "1"
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

            Column(modifier = Modifier.fillMaxSize()) {
//                Surface(
//                   // border = BorderStroke(1.dp, Color.Gray),
//                    //shape = RoundedCornerShape(4.dp),
//                    //backgroundColor = Color.Yellow
//                ) {
                Row(
                    modifier = Modifier.fillMaxSize()

                ) {

                    Row(modifier = Modifier
                        .clickable { mExpanded = !mExpanded }
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(start = 15.dp)) {
                         Text(
                            //text = if (mSelectedText=="Select Plant") mSelectedText else "Plant${mSelectedText}",
                            text = "Plant${mSelectedText}",
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
                //     }

//------------------------------------------------------------------------------------//


                DropdownMenu(
                    modifier = Modifier.wrapContentHeight(),
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                ) {
                    dataList.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(text = "Plant${item.plant_no}")
                                    Divider(
                                        color = Color.LightGray,
                                        thickness = 0.8.dp,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                }
                            },
                            onClick = {
                                mSelectedText = item.plant_no
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