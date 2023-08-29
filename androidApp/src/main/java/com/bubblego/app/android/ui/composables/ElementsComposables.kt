package com.bubblego.app.android.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bubblego.app.android.R
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.theme.bubbleCarCheckboxColors
import com.bubblego.app.android.theme.bubbleCarGooglePayButtonColors
import com.bubblego.app.android.theme.bubbleCarOutlinedColors
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.pay.button.ButtonTheme
import com.google.pay.button.ButtonType
import com.google.pay.button.PayButton

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
fun PrimaryButton(text: String, modifier: Modifier, enabled: Boolean, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun TimePickerButton(
    text: String,
    modifier: Modifier,
    colors: ButtonColors,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(50),
        elevation = null,
        colors = colors
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            fontSize = 35.sp,
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
            ServiceCheckBox(
                text = "Exterior",
                price = "50",
                modifier = Modifier,
                isChecked = remember { mutableStateOf(true) })
        }
    }
}

@Preview
@Composable
fun SuccessDialogPreview() {
    BubbleCarTheme {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            SuccessDialog(
                modifier = Modifier
                    .width(100.dp)
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun ServiceCheckBox(
    text: String,
    price: String,
    modifier: Modifier,
    isChecked: MutableState<Boolean>
) {
    Box(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.primaryVariant,
                MaterialTheme.shapes.small
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
            painter = painterResource(R.drawable.location_pin_circle),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 5.dp)
                .scale(0.05f)
        )

        Image(
            painter = painterResource(R.drawable.location_pin),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = imagePadding)
                .scale(0.15f)
        )
    }
}

@Composable
fun CustomEditText(
    modifier: Modifier,
    text: TextFieldValue,
    placeHolder: String,
    label: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = text,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center
        ),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = placeHolder,
                    textAlign = TextAlign.Center
                )
            }
        },
        label = {
            Text(
                text = label,
                textAlign = TextAlign.Center,
            )
        },
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters,
        ),
        singleLine = true,
        colors = bubbleCarOutlinedColors()
    )
}

@Composable
fun CustomGooglePayButton(
    modifier: Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 50.dp),
        onClick = onClick,
        colors = bubbleCarGooglePayButtonColors(),
        shape = RoundedCornerShape(50),
        enabled = enabled
    ) {
        Text(
            text = "Pay with",
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
        )

        Icon(
            painter = painterResource(id = R.drawable.google_pay_logo_svg),
            modifier = Modifier
                .scale(0.8f)
                .padding(start = 10.dp),
            contentDescription = "drawable icons",
            tint = Color.Unspecified
        )
    }
}

@Composable
fun SuccessDialog(
    modifier: Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success_dialog_animation))

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colors.background,
            shape = RoundedCornerShape(8.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (
                    successAnimation,
                    dialogTitle
                ) = createRefs()

                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(150.dp)
                        .constrainAs(successAnimation) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                SubTitle(
                    text = "Payment Successful",
                    modifier = Modifier.constrainAs(dialogTitle) {
                        top.linkTo(successAnimation.bottom)
                        bottom.linkTo(parent.bottom, margin = 25.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    }
}