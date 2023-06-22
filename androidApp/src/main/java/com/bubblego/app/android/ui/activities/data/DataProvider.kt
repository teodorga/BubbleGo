package com.bubblego.app.android.ui.activities.data

import androidx.compose.runtime.mutableStateOf
import com.bubblego.app.android.ui.models.UiService

object DataProvider {

    const val TAG = "BubbleGo_android"

    val servicesList = listOf(
        UiService(
            0, mutableStateOf(false), "Exterior", "75"
        ),
        UiService(
            1, mutableStateOf(false), "Wheels", "20"
        ),
        UiService(
            2, mutableStateOf(false), "Back/Front Lights", "30"
        ),
    )

}