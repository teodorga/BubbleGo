package com.bubblego.app.android.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.theme.bubbleCarCheckboxColors

@Composable
fun HolderBox(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.secondary,
                MaterialTheme.shapes.large
            )
    )
}

@Composable
fun PrimaryButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun ServiceCheckBoxPreview() {
    BubbleCarTheme {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            ServiceCheckBox(text = "Exterior", price = "50", modifier = Modifier)
        }
    }
}

@Composable
fun ServiceCheckBox(text: String, price: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.primaryVariant,
                MaterialTheme.shapes.medium
            )
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (
                checkBox,
                serviceText,
                servicePriceText
            ) = createRefs()

            val isChecked = remember {
                mutableStateOf(false)
            }

            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
                modifier = Modifier
                    .scale(1.7f)
                    .constrainAs(checkBox) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 15.dp)
                    },
                colors = bubbleCarCheckboxColors()
            )

            SubTitle(
                text = text,
                modifier = Modifier.constrainAs(serviceText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(checkBox.end)
                    end.linkTo(servicePriceText.start)
                }
            )

            LightPriceText(
                text = price,
                modifier = Modifier.constrainAs(servicePriceText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 20.dp)
                }
            )
        }
    }
}