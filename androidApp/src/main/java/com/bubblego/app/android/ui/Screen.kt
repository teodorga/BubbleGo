package com.bubblego.app.android.ui

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object NewAppServices : Screen("new_app_services_screen")
    object NewAppLocation : Screen("new_app_location_screen")
    object NewAppDate : Screen("new_app_date_screen")
    object NewAppDetails : Screen("new_app_details_screen")
    object ConfirmPay : Screen("confirm_pay_screen")
}
