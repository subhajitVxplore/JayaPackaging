package com.jaya.app.packaging.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.core.utils.NavigationIntent
import com.jaya.app.core.common.Destination
import com.jaya.app.packaging.presentation.ui.screen.LoginScreen
import com.jaya.app.packaging.presentation.ui.screen.OtpScreen
import com.jaya.app.packaging.presentation.ui.screen.SplashScreen
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel

import kotlinx.coroutines.channels.Channel

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    navigationChannel: Channel<NavigationIntent>,
    paddingValues: PaddingValues,
    baseViewModel: BaseViewModel
) {
    navHostController.NavEffects(navigationChannel)

    AppNavHost(
        navController = navHostController,
        startDestination = Destination.Splash,
        modifier = Modifier.padding(paddingValues),
        enterTransition = AppScreenTransitions.ScreenEnterTransition,
        popEnterTransition = AppScreenTransitions.ScreenPopEnterTransition,
        exitTransition = AppScreenTransitions.ScreenExitTransition,
        popExitTransition = AppScreenTransitions.ScreenPopExitTransition,
    ) {

        composable(destination = Destination.Splash) {
            SplashScreen()
        }

        composable(destination = Destination.Login) {
            LoginScreen(navHostController)
        }

        composable(destination = Destination.Otp) {
            OtpScreen(navHostController)
        }


    }
}