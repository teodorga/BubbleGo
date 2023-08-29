package com.bubblego.app.android.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bubblego.app.android.ui.activities.MainFlowViewModel
import com.bubblego.app.android.ui.activities.NewAppointmentViewModel
import com.bubblego.app.android.ui.screens.*

@Composable
fun NewAppointmentNavGraph(
    navController: NavHostController
) {

    val viewModel: NewAppointmentViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.NewAppServices.route
    ) {

        composable(
            route = Screen.NewAppServices.route
        ) {
            NewAppServicesScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.NewAppLocation.route
        ) {
            NewAppLocationScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.NewAppDate.route
        ) {
            NewAppDateScreen(
                navController = navController,
                viewModel = viewModel
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
                navController = navController,
                viewModel = viewModel
            )
        }

    }
}

@Composable
fun MainNavGraph(
    navController: NavHostController
) {

    val viewModel: MainFlowViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {

        composable(
            route = Screen.Welcome.route
        ) {
            WelcomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

    }
}
