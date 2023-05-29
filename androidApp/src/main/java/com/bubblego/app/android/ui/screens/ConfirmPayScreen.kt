package com.bubblego.app.android.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.ui.composables.*
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Preview
@Composable
fun ConfirmPayPreview() {
    ConfirmPayScreen(navController = rememberNavController())
}

@Composable
fun ConfirmPayScreen(
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
            ConstraintLayoutConfirmPay(
                navController = navController
            )
        }
    }
}

@Composable
fun ConstraintLayoutConfirmPay(
    navController: NavController
) {
    var showSuccessDialog by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            welcomeTitle,
            stepSubTitle,
            descriptionText,
            googlePayButton,
        ) = createRefs()

        val context = LocalContext.current as Activity


        WelcomeTitle(
            text = "Payment",
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(parent.top, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        SubTitle(
            text = "Step 4/4",
            modifier = Modifier.constrainAs(stepSubTitle) {
                top.linkTo(welcomeTitle.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        DescriptionText(
            text = "Choose a payment method",
            modifier = Modifier.constrainAs(descriptionText) {
                top.linkTo(stepSubTitle.bottom, margin = 60.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        CustomGooglePayButton(
            modifier = Modifier.constrainAs(googlePayButton) {
                top.linkTo(descriptionText.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            showSuccessDialog = true
        }

        if (showSuccessDialog) {
            SuccessDialog(
                modifier = Modifier
                    .height(200.dp)
                    .width(250.dp)
            )

            LaunchedEffect(Unit) {
                delay(2.seconds)
                showSuccessDialog = false
                context.finish()
            }
        }
    }
}