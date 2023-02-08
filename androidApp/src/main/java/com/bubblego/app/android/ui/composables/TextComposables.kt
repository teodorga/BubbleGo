package com.bubblego.app.android.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun SubTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun DescriptionText(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun SecondDescriptionText(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun BoldPriceText(text: String, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WelcomeTitle(
            text = text,
            modifier = Modifier
        )

        WelcomeTitle(
            text = "Lei",
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun LightPriceText(text: String, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DescriptionText(
            text = text,
            modifier = Modifier
        )

        DescriptionText(
            text = "Lei",
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Composable
fun TotalPriceText(text: String, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubTitle(
            text = "Total:",
            modifier = Modifier
        )

        BoldPriceText(
            text = text,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}