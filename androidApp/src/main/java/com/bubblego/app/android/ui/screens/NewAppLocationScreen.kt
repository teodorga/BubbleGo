package com.bubblego.app.android.ui.screens

import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.ui.Screen
import com.bubblego.app.android.ui.composables.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.*

@Preview
@Composable
fun NewAppLocationPreview() {
    NewAppLocationScreen(navController = rememberNavController())
}

@Composable
fun NewAppLocationScreen(
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
            ConstraintLayoutNewAppLocation(
                navController = navController
            )
        }
    }
}

@Composable
fun ConstraintLayoutNewAppLocation(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            welcomeTitle,
            stepSubTitle,
            descriptionText,
            mapHolder,
            secondDescriptionText,
            selectLocationButton
        ) = createRefs()

        val context = LocalContext.current

        var locationTextHolder by remember {
            mutableStateOf("Place the marker on the map accordingly")
        }

        WelcomeTitle(
            text = "Location",
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(parent.top, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        SubTitle(
            text = "Step 2/4",
            modifier = Modifier.constrainAs(stepSubTitle) {
                top.linkTo(welcomeTitle.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        DescriptionText(
            text = "Now, we need to know where your car is parked",
            modifier = Modifier.constrainAs(descriptionText) {
                top.linkTo(stepSubTitle.bottom, margin = 60.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        val currentLocation = LatLng(
            46.773016, 23.623153
        )
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                currentLocation,
                15f
            )
        }

        RoundedLocationMap(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(mapHolder) {
                    top.linkTo(descriptionText.bottom, margin = 14.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(selectLocationButton.top, margin = 75.dp)
                    height = Dimension.fillToConstraints
                },
            cameraPositionState = cameraPositionState
        )

        SecondDescriptionText(
            text = locationTextHolder,
            modifier = Modifier.constrainAs(secondDescriptionText) {
                top.linkTo(mapHolder.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        val geocoder = Geocoder(context, Locale.getDefault())

        PrimaryButton(
            text = "Select location",
            modifier = Modifier.constrainAs(selectLocationButton) {
                bottom.linkTo(parent.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {

            if (!cameraPositionState.isMoving) {
                val address = geocoder.getFromLocation(
                    cameraPositionState.position.target.latitude,
                    cameraPositionState.position.target.longitude,
                    1,
                )
                locationTextHolder = address!![0].getAddressLine(0)

                navController.navigate(
                    route = Screen.NewAppDetails.route
                )
            }

            Log.d(
                "Location", cameraPositionState.position.target.latitude.toString() + " " +
                        cameraPositionState.position.target.longitude.toString()
            )
        }
    }
}