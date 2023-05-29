package com.bubblego.app.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bubblego.app.android.ui.screens.*

@Composable
fun NewAppointmentNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewAppServices.route
    ) {

        composable(
            route = Screen.NewAppServices.route
        ) {
            NewAppServicesScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.NewAppLocation.route
        ) {
            NewAppLocationScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.NewAppDetails.route
        ) {
            NewAppDetailsScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.ConfirmPay.route
        ) {
            ConfirmPayScreen(
                navController = navController
            )
        }

    }
}

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {

        composable(
            route = Screen.Welcome.route
        ) {
            WelcomeScreen(
                navController = navController
            )
        }

    }
}
