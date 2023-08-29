package com.bubblego.app.android.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bubblego.app.android.theme.BubbleCarTheme
import com.bubblego.app.android.theme.bubbleCarDatePickerColors
import com.bubblego.app.android.ui.Screen
import com.bubblego.app.android.ui.activities.NewAppointmentViewModel
import com.bubblego.app.android.ui.composables.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewAppDatePreview() {
    NewAppDateScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAppDateScreen(
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
            ConstraintLayoutNewAppDate(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstraintLayoutNewAppDate(
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
            datePicker,
            invisibleBox,
            timePickerList,
            selectLocationButton
        ) = createRefs()

        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
        val dateAndTimeList = com.bubblego.app.android.ui.activities.data.DataProvider.dateAndTime
        val primaryButtonEnabledStatus = remember { mutableStateOf(false) }

        val primaryButtonText = remember {
            mutableStateOf("Select date")
        }

        if (state.displayMode == DisplayMode.Input) {
            primaryButtonText.value = "Continue"
        } else {
            primaryButtonText.value = "Select date"
        }

        if (state.selectedDateMillis == null) {
            viewModel.selectedDate.value = 0
        } else {
            viewModel.selectedDate.value = state.selectedDateMillis!!
        }


        WelcomeTitle(
            text = "Date & Time",
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(parent.top, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        SubTitle(
            text = "Step 3/5",
            modifier = Modifier.constrainAs(stepSubTitle) {
                top.linkTo(welcomeTitle.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .height(50.dp)
                .fillMaxWidth()
                .zIndex(3f)
                .constrainAs(descriptionText) {
                    top.linkTo(stepSubTitle.bottom, margin = 60.dp)
                }
        ) {
            DescriptionText(
                text = "Choose the day and the time when to visit your car.",
                modifier = Modifier
            )
        }

        DatePicker(
            state = state,
            dateValidator = { date ->
                date > System.currentTimeMillis() &&
                        viewModel.bookedTimes.value.filter { element ->
                            element.first == SimpleDateFormat(
                                "dd.MM.yyyy", Locale.getDefault()
                            ).format(Date(date)).toString()
                        }.map {
                            it.second
                        }.size < 13
            },
            title = {
                Text(text = "")
            },
            headline = {
                Text(text = "")
            },
            dateFormatter = DatePickerFormatter(),
            showModeToggle = false,
            colors = bubbleCarDatePickerColors(),
            modifier = Modifier
                .zIndex(2f)
                .constrainAs(datePicker) {
                    top.linkTo(descriptionText.bottom, margin = (-130).dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        if (state.displayMode == DisplayMode.Input) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .zIndex(3f)
                    .alpha(0f)
                    .clickable(enabled = false, onClick = {})
                    .constrainAs(invisibleBox) {
                        top.linkTo(descriptionText.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

        BackHandler(true) {
            if (state.displayMode == DisplayMode.Input) {
                state.displayMode = DisplayMode.Picker
                primaryButtonText.value = "Select date"
            } else {
                navController.popBackStack()
            }
        }

        TimePickerList(
            list = com.bubblego.app.android.ui.activities.data.DataProvider.dateAndTime,
            lastSelected = viewModel.selectedTime,
            bookedTime = viewModel.bookedTimes.value.filter {
                it.first == SimpleDateFormat(
                    "dd.MM.yyyy", Locale.getDefault()
                ).format(Date(viewModel.selectedDate.value)).toString()
            }.map {
                it.second
            },
            modifier = Modifier
                .alpha(if (state.displayMode == DisplayMode.Input) 1f else 0f)
                .wrapContentHeight(Alignment.CenterVertically)
                .constrainAs(timePickerList) {
                    top.linkTo(descriptionText.bottom, margin = 90.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(selectLocationButton.top, margin = 20.dp)
                    height = Dimension.fillToConstraints
                }
        )

        if (viewModel.selectedDate.value != 0.toLong() && state.displayMode == DisplayMode.Picker) {
            primaryButtonEnabledStatus.value = true
        }

        if (viewModel.selectedTime.value != "0" && state.displayMode == DisplayMode.Input) {
            primaryButtonEnabledStatus.value = true
        } else if (viewModel.selectedTime.value == "0" && state.displayMode == DisplayMode.Input) {
            primaryButtonEnabledStatus.value = false
        }


        PrimaryButton(
            text = primaryButtonText.value,
            enabled = primaryButtonEnabledStatus.value,
            modifier = Modifier.constrainAs(selectLocationButton) {
                bottom.linkTo(parent.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            when (state.displayMode) {
                DisplayMode.Picker -> {
                    state.displayMode = DisplayMode.Input
                    primaryButtonEnabledStatus.value = false
                }
                DisplayMode.Input -> {
                    navController.navigate(
                        Screen.NewAppDetails.route
                    )
                }
            }
        }
    }
}