package com.jaya.app.packaging.presentation.ui.custom_view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.core.domain.models.ProductType
import com.jaya.app.packaging.extensions.bottomToUp
import com.jaya.app.packaging.extensions.screenHeight
import com.jaya.app.packaging.extensions.screenWidth
import com.jaya.app.packaging.extensions.upToBottom
import com.jaya.app.packaging.presentation.viewModels.AddPackingDetailsViewModel
import com.jaya.app.packaging.presentation.viewModels.AddProductViewModel
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import com.jaya.app.packaging.ui.theme.SplashGreen
import java.util.ArrayList
import java.util.Locale

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LabourSearchDropdown(
    viewModel: AddPackingDetailsViewModel,
    loading: Boolean,
    dataList: List<String>,
    onSelect: (String) -> Unit
) {//need to inherit "DropDownItem" to model classes

    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }

    //  mSelectedText=baseViewModel.prefilledExpenseType
    if (mSelectedText.isEmpty()) {
        mSelectedText = viewModel.packingLabourSearchQuery.value
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

            Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {

                        Row(modifier = Modifier
                            //.clickable { mExpanded = !mExpanded }
                            .fillMaxHeight().fillMaxWidth()
                            .weight(1f)) {


                            OutlinedTextField(
                                value = mSelectedText,
                                onValueChange = {
                                    mExpanded = true
                                    mSelectedText = it

                                                },
                                //label = { Text("your mobile number") },
                                placeholder = { Text("Search Packing Labour", color = Color.Gray) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Gray,
                                    unfocusedBorderColor = Color.Gray
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .padding(end = 5.dp),
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(32.dp)
                                            .width(32.dp),
                                        tint = Color.LightGray
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .clickable { mSelectedText="" }
                                            .height(32.dp)
                                            .width(32.dp),
                                        tint = Color.LightGray
                                    )
                                },

                            )
                        }
                        Button(
                            contentPadding = PaddingValues(0.dp),
                            onClick = {
                               // viewModel.showWorkersNameDialog.value = true  //for Dialog
                                viewModel.packingLabourList.add(mSelectedText)
                                mSelectedText=""
                            },
                            colors = ButtonDefaults.buttonColors(SplashGreen),
                            modifier = Modifier.height(55.dp).width(55.dp).align(Alignment.CenterVertically),
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Icon(
                                Icons.Default.Add,
                                tint = Color.White,
                                contentDescription = "",
                                // modifier = Modifier.padding(end = 8.dp)
                            )
                            //Icon(Icons.Filled.Add, "add")
                        }

                    }
          //      }

//------------------------------------------------------------------------------------//

                var filteredList: List<String>
                DropdownMenu(
                    modifier= Modifier.wrapContentHeight(),
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                ) {

                    val searchedText = mSelectedText
                    filteredList = if (searchedText.isEmpty()) {
                        dataList
                    } else {
                        val resultList = ArrayList<String>()
                        for (datum in dataList) {
                          //  if (datum.lowercase(Locale.getDefault()).contains(searchedText.lowercase(Locale.getDefault()))
                            if (datum.lowercase(Locale.getDefault()).startsWith(searchedText.lowercase(Locale.getDefault()))) {


                                resultList.add(datum)
                            }
                        }
                        resultList
                    }

                    filteredList.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(text = if (item.startsWith(mSelectedText)) item else item)
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



