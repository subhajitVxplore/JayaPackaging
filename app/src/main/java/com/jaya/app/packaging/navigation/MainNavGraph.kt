package com.jaya.app.packaging.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.utils.NavigationIntent
import com.jaya.app.core.common.Destination
import com.jaya.app.packaging.presentation.ui.custom_view.DetailsScreen
import com.jaya.app.packaging.presentation.ui.screen.AddPackingDetailsScreen
import com.jaya.app.packaging.presentation.ui.screen.AddProductScreen
import com.jaya.app.packaging.presentation.ui.screen.DashboardScreen
import com.jaya.app.packaging.presentation.ui.screen.FinalReportScreen
import com.jaya.app.packaging.presentation.ui.screen.LoginScreen
import com.jaya.app.packaging.presentation.ui.screen.OtpScreen
import com.jaya.app.packaging.presentation.ui.screen.ProductionReportScreen
import com.jaya.app.packaging.presentation.ui.screen.RepportSubmitSuccessScreen
import com.jaya.app.packaging.presentation.ui.screen.SplashScreen
import com.jaya.app.packaging.presentation.ui.screen.VideoCaptureScreen
import com.jaya.app.packaging.presentation.viewModels.BaseViewModel
import kotlinx.coroutines.channels.Channel

@RequiresApi(Build.VERSION_CODES.P)
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
        startDestination = Destination.Dashboard,
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
            LoginScreen(baseViewModel)
        }

        composable(destination = Destination.Otp) {
            OtpScreen(baseViewModel)
        }
        composable(destination = Destination.Dashboard) {
            DashboardScreen(baseViewModel)
        }
        composable(destination = Destination.AddProduct) {
            AddProductScreen(baseViewModel)
        }
        composable(destination = Destination.AddPackingDetails) {
            AddPackingDetailsScreen(baseViewModel)
        }
        composable(destination = Destination.ProductionReport) {
            ProductionReportScreen(baseViewModel)
        }
        composable(destination = Destination.ReportSubmitSuccess) {
            RepportSubmitSuccessScreen(baseViewModel)
        }
        composable(destination = Destination.FinalReport) {
            FinalReportScreen(baseViewModel)
        }

        composable(destination = Destination.CaptureVideo) {
            VideoCaptureScreen(baseViewModel)
        }

        composable(
            "details/{countryName}",
            arguments = listOf(navArgument("countryName") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("countryName")?.let { countryName ->
                DetailsScreen(countryName = countryName)
            }
        }

    }
}