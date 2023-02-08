package com.bubblego.app.android.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.theme.bubbleCarCheckboxColors
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

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

@Preview
@Composable
fun RoundedLocationMapPreview() {
    val currentLocation = LatLng(
        46.773016, 23.623153
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            currentLocation,
            15f
        )
    }
    RoundedLocationMap(modifier = Modifier, cameraPositionState)
}


@Composable
fun RoundedLocationMap(
    modifier: Modifier,
    cameraPositionState: CameraPositionState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(
                shape = MaterialTheme.shapes.large
            ),
        contentAlignment = Alignment.Center
    ) {
        val uiSetting = remember {
            MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = false
            )
        }

        val properties by remember {
            mutableStateOf(MapProperties(isMyLocationEnabled = true))
        }

        var imagePadding by remember {
            mutableStateOf(50.dp)
        }

        GoogleMap(
            uiSettings = uiSetting,
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,

            ) {
            imagePadding = if (cameraPositionState.isMoving) {
                70.dp
            } else {
                50.dp
            }
        }

        Image(
            painter = painterResource(com.bubblego.app.android.R.drawable.location_pin_circle),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 5.dp)
                .scale(0.05f)
        )

        Image(
            painter = painterResource(com.bubblego.app.android.R.drawable.location_pin),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = imagePadding)
                .scale(0.15f)
        )
    }
}