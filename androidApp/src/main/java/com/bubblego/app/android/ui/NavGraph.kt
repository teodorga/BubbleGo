package com.bubblego.app.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bubblego.app.android.ui.screens.NewAppLocationScreen
import com.bubblego.app.android.ui.screens.NewAppServicesScreen
import com.bubblego.app.android.ui.screens.WelcomeScreen

@Composable
fun NavGraph(
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
    }
}
