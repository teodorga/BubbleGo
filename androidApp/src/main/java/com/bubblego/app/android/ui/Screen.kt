package com.bubblego.app.android.ui

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
}
