package com.jaya.app.packaging.presentation.ui.screen

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jaya.app.packaging.R
import com.jaya.app.packaging.presentation.ui.custom_view.OtpTextField
import com.jaya.app.packaging.presentation.viewModels.DashboardViewModel
import com.jaya.app.packaging.presentation.viewModels.LoginViewModel
import com.jaya.app.packaging.presentation.viewModels.OtpViewModel
import com.jaya.app.packaging.ui.theme.AppBarYellow
import com.jaya.app.packaging.ui.theme.LighrYellow
import com.jaya.app.packaging.ui.theme.LogoutRed
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val currentScreen = remember { mutableStateOf(DrawerAppScreen.Home) }
    val coroutineScope = rememberCoroutineScope()
    // val scaffoldState = rememberScaffoldState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val ctx = LocalContext.current
    val activity = LocalContext.current as Activity


    ModalNavigationDrawer(
        drawerState=drawerState,
        modifier = Modifier.fillMaxSize(),
        drawerContent = {

            DrawerContentComponent(
                currentScreen = currentScreen,
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                viewModel
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(AppBarYellow)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                ) {
                    IconButton(
                        modifier = Modifier.padding(top = 5.dp),
                        onClick = {
                            coroutineScope.launch { drawerState.open() }
                            // Toast.makeText(ctx, "drawer contents", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(Icons.Filled.Menu, "")
                    }

                    Image(
                        painter = painterResource(id = R.drawable.cropped_logo),
                        contentDescription = "jayaLogo",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                        //.padding(70.dp, 0.dp, 0.dp, 0.dp)
                    )

                    IconButton(
                        onClick = {
                            // viewModel.onBackDialog()
                            Toast.makeText(ctx, "Notifications", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 5.dp)
                    ) {
                        Icon(Icons.Filled.Notifications, "")
                    }
                }
            }

            BodyContentComponent(
                currentScreen = currentScreen.value,
                openDrawer = { coroutineScope.launch { drawerState.open() } },
                viewModel = hiltViewModel()
            )
        }
    }
//-------------------------------------------------------------------------//



}//DashboardScreen

///////////////////////////////////////////////////////////////////////////////////////////////
enum class DrawerAppScreen { Home }

fun getScreenBasedOnIndex(index: Int) = when (index) {
    0 -> DrawerAppScreen.Home
    else -> DrawerAppScreen.Home
}

@Composable
fun BodyContentComponent(
    currentScreen: DrawerAppScreen,
    openDrawer: () -> Unit,
    viewModel: DashboardViewModel,
) {
    when (currentScreen) {
        DrawerAppScreen.Home -> HomePage(openDrawer, viewModel)
    }
}

@Composable
fun DrawerContentComponent(currentScreen: MutableState<DrawerAppScreen>, closeDrawer: () -> Unit, viewModel: DashboardViewModel) {

    Column(modifier = Modifier
        .fillMaxHeight()
        .width(270.dp)
        .background(LighrYellow)) {

        Spacer(modifier = Modifier.padding(top = 80.dp))

        for (index in DrawerAppScreen.values().indices) {
            val screen = getScreenBasedOnIndex(index)
            Column(Modifier.clickable(onClick = {
                currentScreen.value = screen
                closeDrawer()
            }), content = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = if (currentScreen.value == screen) {
                        Color.Cyan
                    } else {
                        Color.Blue
                    }
                ) {
                    Text(text = screen.name, modifier = Modifier.padding(16.dp))
                }
            })
        }


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(LogoutRed)
                .clickable {
                    //viewModel.onLogoutFromDashboard()
                }) {

                Text(
                    text = "Logout",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp).weight(1f)
                )

                Image(painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "LogOut img",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp)
                )

            }
        }
    }
}