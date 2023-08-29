package com.bubblego.app.android.controller.models

import androidx.compose.runtime.MutableState

data class UiTime(
    val hour: String,
    var selected: MutableState<Boolean>
)