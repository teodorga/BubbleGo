package com.bubblego.app.android.ui.models

import androidx.compose.runtime.MutableState

data class UiService(
    val id: Int,
    val checkboxStatus: MutableState<Boolean>,
    val serviceTitle: String,
    val priceText: String
)