package com.bubblego.app.android.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun bubbleCarCheckboxColors(): CheckboxColors {
    return CheckboxDefaults.colors(
        checkedColor = MaterialTheme.colors.background,
        uncheckedColor = MaterialTheme.colors.background,
        checkmarkColor = MaterialTheme.colors.primary
    )
}

@Composable
fun bubbleCarUnselectedTimePickerButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface
    )
}

@Composable
fun bubbleCarSelectedTimePickerButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.background
    )
}

@Composable
fun bubbleCarGooglePayButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background
    )
}