package com.bubblego.app.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.ui.Screen
import com.bubblego.app.android.ui.composables.*

@Preview
@Composable
fun NewAppDetailsPreview() {
    NewAppDetailsScreen(navController = rememberNavController())
}

@Composable
fun NewAppDetailsScreen(
    navController: NavController
) {
    BubbleCarTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            ConstraintLayoutNewAppDetails(
                navController = navController
            )
        }
    }
}

@Composable
fun ConstraintLayoutNewAppDetails(
    navController: NavController
) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 10)))
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            editTextList
        ) = createRefs()

        Column(
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .verticalScroll(rememberScrollState())
                .constrainAs(editTextList) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (
                    welcomeTitle,
                    stepSubTitle,
                    descriptionText,
                    nameEditText,
                    surnameEditText,
                    carPlateEditText,
                    carBrandEditText,
                    carModelEditText,
                    carColorEditText,
                    continueButton
                ) = createRefs()

                WelcomeTitle(
                    text = "Additional details",
                    modifier = Modifier.constrainAs(welcomeTitle) {
                        top.linkTo(parent.top, margin = 50.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                SubTitle(
                    text = "Step 3/4",
                    modifier = Modifier.constrainAs(stepSubTitle) {
                        top.linkTo(welcomeTitle.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                DescriptionText(
                    text = "We need some more details",
                    modifier = Modifier.constrainAs(descriptionText) {
                        top.linkTo(stepSubTitle.bottom, margin = 60.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                CustomEditText(
                    modifier = Modifier
                        .width(250.dp)
                        .constrainAs(nameEditText) {
                            top.linkTo(descriptionText.bottom, margin = 31.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = text,
                    placeHolder = "John",
                    label = "Name"
                ) {
                    text = it
                }

                CustomEditText(
                    modifier = Modifier
                        .width(250.dp)
                        .constrainAs(surnameEditText) {
                            top.linkTo(nameEditText.bottom, margin = 31.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = text,
                    placeHolder = "John",
                    label = "Surname"
                ) {
                    text = it
                }

                CustomEditText(
                    modifier = Modifier
                        .width(250.dp)
                        .constrainAs(carPlateEditText) {
                            top.linkTo(surnameEditText.bottom, margin = 31.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = text,
                    placeHolder = "NT-99-GTR",
                    label = "Car plate number"
                ) {
                    text = it
                }

                CustomEditText(
                    modifier = Modifier
                        .width(250.dp)
                        .constrainAs(carBrandEditText) {
                            top.linkTo(carPlateEditText.bottom, margin = 31.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = text,
                    placeHolder = "Hyundai",
                    label = "Car brand"
                ) {
                    text = it
                }

                CustomEditText(
                    modifier = Modifier
                        .width(250.dp)
                        .constrainAs(carModelEditText) {
                            top.linkTo(carBrandEditText.bottom, margin = 31.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = text,
                    placeHolder = "Hyundai",
                    label = "Car model"
                ) {
                    text = it
                }

                CustomEditText(
                    modifier = Modifier
                        .width(250.dp)
                        .constrainAs(carColorEditText) {
                            top.linkTo(carModelEditText.bottom, margin = 31.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = text,
                    placeHolder = "Hyundai",
                    label = "Car color"
                ) {
                    text = it
                }

                PrimaryButton(
                    text = "Continue",
                    modifier = Modifier.constrainAs(continueButton) {
                        top.linkTo(carColorEditText.bottom, margin = 50.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = 50.dp)
                    }
                ) {
                    navController.navigate(
                        route = Screen.ConfirmPay.route
                    )
                }
            }
        }
    }
}