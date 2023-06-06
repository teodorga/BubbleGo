package com.bubblego.app.android.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.ui.activities.NewAppointmentActivity
import com.bubblego.app.android.ui.composables.HolderBox
import com.bubblego.app.android.ui.composables.PrimaryButton
import com.bubblego.app.android.ui.composables.SubTitle
import com.bubblego.app.android.ui.composables.WelcomeTitle

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}

@Composable
fun WelcomeScreen(
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
            ConstraintLayoutContent(
                navController = navController
            )
        }
    }
}

@Composable
fun ConstraintLayoutContent(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            welcomeText,
            appointmentsTitle,
            appointmentsList,
            appointmentButton
        ) = createRefs()

        val context = LocalContext.current

        WelcomeTitle(
            text = "Welcome!",
            modifier = Modifier.constrainAs(welcomeText) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        SubTitle(
            text = "Your appointments",
            modifier = Modifier.constrainAs(appointmentsTitle) {
                start.linkTo(parent.start, margin = 5.dp)
                bottom.linkTo(appointmentsList.top, margin = 5.dp)
            }
        )

        HolderBox(
            modifier = Modifier
                .height(300.dp)
                .constrainAs(appointmentsList) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(appointmentButton.top, margin = 50.dp)
                })

        PrimaryButton(
            text = "New appointment",
            modifier = Modifier.constrainAs(appointmentButton) {
                bottom.linkTo(parent.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            context.startActivity(
                Intent(context, NewAppointmentActivity::class.java)
            )
        }
    }
}