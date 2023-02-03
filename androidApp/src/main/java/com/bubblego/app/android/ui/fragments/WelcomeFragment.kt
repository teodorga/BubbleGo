package com.bubblego.app.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bubblego.app.android.theme.BubbleCarTheme

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MainFlowView()
            }
        }
    }
}

@Preview
@Composable
fun MainFlowView() {
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
            ConstraintLayoutContent()
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
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

        WelcomeTitle(
            text = "Welcome!",
            modifier = Modifier.constrainAs(welcomeText) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        AppointmentsTitle(
            text = "Your appointments",
            modifier = Modifier.constrainAs(appointmentsTitle) {
                start.linkTo(parent.start, margin = 5.dp)
                bottom.linkTo(appointmentsList.top, margin = 5.dp)
            }
        )

        AppointmentsList(
            modifier = Modifier.constrainAs(appointmentsList) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(appointmentButton.top, margin = 50.dp)
            })

        AppointmentButton(
            text = "New appointment",
            modifier = Modifier.constrainAs(appointmentButton) {
                bottom.linkTo(parent.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

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
fun AppointmentsTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun AppointmentsList(modifier: Modifier) {
    Box(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.secondary,
                MaterialTheme.shapes.large
            )
    )
}

@Composable
fun AppointmentButton(text: String, modifier: Modifier) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            /*TODO*/
        }) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
            textAlign = TextAlign.Center,
        )
    }
}