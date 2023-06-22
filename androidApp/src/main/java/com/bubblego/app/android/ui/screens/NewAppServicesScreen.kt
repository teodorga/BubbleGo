package com.bubblego.app.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.ui.Screen
import com.bubblego.app.android.ui.activities.NewAppointmentViewModel
import com.bubblego.app.android.ui.composables.*


@Preview
@Composable
fun NewAppServicesPreview() {
    NewAppServicesScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}

@Composable
fun NewAppServicesScreen(
    navController: NavController,
    viewModel: NewAppointmentViewModel
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
            ConstraintLayoutNewAppServices(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun ConstraintLayoutNewAppServices(
    navController: NavController,
    viewModel: NewAppointmentViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            welcomeTitle,
            stepSubTitle,
            descriptionText,
            servicesList,
            totalText,
            continueButton
        ) = createRefs()

        val services = viewModel.selectedServices.value

        LaunchedEffect(services.map {
            it.checkboxStatus.value
        }) {
            viewModel.computeFinalPrice(services)
        }

        WelcomeTitle(
            text = "Choose the services",
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(parent.top, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        SubTitle(
            text = "Step 1/4",
            modifier = Modifier.constrainAs(stepSubTitle) {
                top.linkTo(welcomeTitle.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        DescriptionText(
            text = "Firstly, select what type of cleaning services do you need for your car",
            modifier = Modifier.constrainAs(descriptionText) {
                top.linkTo(stepSubTitle.bottom, margin = 60.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        ServicesList(
            services = services,
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(top = 20.dp, bottom = 20.dp)
                .constrainAs(servicesList) {
                    top.linkTo(descriptionText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(totalText.top)
                    height = Dimension.fillToConstraints
                }
        )

        TotalPriceText(
            text = viewModel.totalPrice.value.toString(),
            modifier = Modifier.constrainAs(totalText) {
                bottom.linkTo(continueButton.top, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }
        )

        PrimaryButton(
            text = "Continue",
            modifier = Modifier.constrainAs(continueButton) {
                bottom.linkTo(parent.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {

            viewModel.selectedServices.value = services

            navController.navigate(
                route = Screen.NewAppLocation.route
            )
        }
    }
}