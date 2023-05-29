package com.bubblego.app.android.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.ui.NewAppointmentNavGraph

class NewAppointmentActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            NewAppointmentNavGraph(navController = navController)
        }

    }
}