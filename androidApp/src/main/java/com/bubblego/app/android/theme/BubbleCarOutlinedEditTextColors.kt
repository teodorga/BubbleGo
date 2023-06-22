package com.bubblego.app.android.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable

@Composable
fun bubbleCarOutlinedColors(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        textColor = MaterialTheme.colors.onSurface,
        unfocusedBorderColor = MaterialTheme.colors.onSurface
    )
}