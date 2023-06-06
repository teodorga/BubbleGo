package com.bubblego.app.android.theme

import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun bubbleCarCheckboxColors(): CheckboxColors {
    return CheckboxDefaults.colors(
        checkedColor = MaterialTheme.colors.background,
        uncheckedColor = MaterialTheme.colors.background,
        checkmarkColor = MaterialTheme.colors.primary
    )
}