package com.jaya.app.packaging.presentation.ui.custom_view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.core.domain.models.ProductType
import com.jaya.app.packaging.extensions.screenHeight

@Composable
fun LaboursSearchResultsSection(
    searchedLabour: State<List<ProductType>?>,
    onSelectLabour: (ProductType) -> Unit,
    // viewModel: VendorBillingViewModel
) {
    //val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    searchedLabour.value?.let {
        AnimatedVisibility(
            visible = it.isNotEmpty(),
            enter = fadeIn(
                tween(400)
            ) + expandVertically(tween(500)),
            exit = fadeOut(tween(400)) + shrinkVertically(tween(500))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(fraction = .90f)
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(12.dp),
                shape = MaterialTheme.shapes.small
            ) {
                LazyColumn(
                    modifier = if (it.size > 10) Modifier
                        .fillMaxWidth()
                        .height(screenHeight * .50f) else Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(it) { labour ->
                        TextButton(onClick = {
                            onSelectLabour(labour)
                            //viewModel.billingDataLoader.value=true
                        }, modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "(${labour})", style = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Start,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
//                        if (vendor != it.last()) {
//                            Divider()
//                        }
                    }
                }
            }
        }
    }
}