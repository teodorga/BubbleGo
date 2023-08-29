package com.bubblego.app.android.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bubbleCarDatePickerColors(): DatePickerColors {
    return DatePickerDefaults.colors(
        selectedDayContainerColor = MaterialTheme.colors.primary,
        todayDateBorderColor = MaterialTheme.colors.primary,
        todayContentColor = MaterialTheme.colors.onSurface,
        disabledDayContentColor = MaterialTheme.colors.primaryVariant,
        selectedYearContainerColor = MaterialTheme.colors.primary,
        currentYearContentColor = MaterialTheme.colors.onSurface
    )
}