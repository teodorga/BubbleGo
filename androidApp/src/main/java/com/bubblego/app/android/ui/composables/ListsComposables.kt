package com.bubblego.app.android.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bubblego.app.android.controller.models.UiService
import com.bubblego.app.android.controller.models.UiTime
import com.bubblego.app.android.theme.bubbleCarSelectedTimePickerButtonColors
import com.bubblego.app.android.theme.bubbleCarUnselectedTimePickerButtonColors

@Composable
fun ServicesList(modifier: Modifier, services: List<UiService>) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(23.dp)
    ) {
        items(services) { service ->
            ServiceCheckBox(
                text = service.serviceTitle,
                price = service.priceText,
                isChecked = service.checkboxStatus,
                modifier = Modifier,
            )
        }
    }
}

@Composable
fun TimePickerList(
    modifier: Modifier,
    list: List<UiTime>,
    lastSelected: MutableState<String>,
    bookedTime: List<String>
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(50.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(list.size) { index ->
            TimePickerButton(
                text = list[index].hour,
                modifier = modifier,
                colors = if (lastSelected.value == list[index].hour) bubbleCarSelectedTimePickerButtonColors() else bubbleCarUnselectedTimePickerButtonColors(),
                enabled = !bookedTime.contains(list[index].hour)
            ) {
                if (lastSelected.value == "0" || (lastSelected.value != "0" && list[index].hour != lastSelected.value)) {
                    list[index].selected.value = true
                    lastSelected.value = list[index].hour
                } else {
                    list[index].selected.value = false
                    lastSelected.value = "0"
                }
            }
        }
    }
}