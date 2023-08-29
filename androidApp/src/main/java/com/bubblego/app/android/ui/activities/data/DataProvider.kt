package com.bubblego.app.android.ui.activities.data

import androidx.compose.runtime.mutableStateOf
import com.bubblego.app.android.controller.models.UiService
import com.bubblego.app.android.controller.models.UiTime

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

    val dateAndTime = listOf(
        UiTime("08:00", mutableStateOf(false)),
        UiTime("09:00", mutableStateOf(false)),
        UiTime("10:00", mutableStateOf(false)),
        UiTime("11:00", mutableStateOf(false)),
        UiTime("12:00", mutableStateOf(false)),
        UiTime("13:00", mutableStateOf(false)),
        UiTime("14:00", mutableStateOf(false)),
        UiTime("15:00", mutableStateOf(false)),
        UiTime("16:00", mutableStateOf(false)),
        UiTime("17:00", mutableStateOf(false)),
        UiTime("18:00", mutableStateOf(false)),
        UiTime("19:00", mutableStateOf(false)),
        UiTime("20:00", mutableStateOf(false))
    )

}