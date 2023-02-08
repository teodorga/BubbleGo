package com.bubblego.app.android.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bubblego.app.android.ui.activities.data.DataProvider

@Composable
fun ServicesList(modifier: Modifier) {
    val services = remember {
        DataProvider.servicesList
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(23.dp)
    ) {
        items(services) { service ->
            ServiceCheckBox(
                text = service.serviceTitle,
                price = service.priceText,
                modifier = Modifier
            )
        }
    }
}