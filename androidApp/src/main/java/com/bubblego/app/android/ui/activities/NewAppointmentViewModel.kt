package com.bubblego.app.android.ui.activities

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bubblego.app.NewAppointmentController
import com.bubblego.app.android.ui.activities.data.DataProvider
import com.bubblego.app.android.ui.models.UiService
import com.bubblego.app.data.Appointment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewAppointmentViewModel @Inject constructor(
    services: List<UiService>,
    private val newAppointmentController: NewAppointmentController
) : ViewModel() {

    val selectedServices: MutableState<List<UiService>> = mutableStateOf(services)
    val location: MutableState<Pair<Double, Double>> = mutableStateOf(Pair(0.0, 0.0))
    var totalPrice: MutableState<Int> = mutableStateOf(0)

    init {
        Log.d(DataProvider.TAG, "NewAppointmentViewMode() init")

        selectedServices.value.map {
            it.checkboxStatus.value = false
        }
    }

    fun computeFinalPrice(services: List<UiService>) {
        var finalPrice = 0
        for (service in services) {
            if (service.checkboxStatus.value) {
                finalPrice += service.priceText.toInt()
            }
        }
        totalPrice.value = finalPrice
    }

    fun saveNewAppointment() {
        val appointment = Appointment(
            services = selectedServices.value.filter { it.checkboxStatus.value }.map {
                it.serviceTitle
            },
            location = location.value,
            details = listOf("test"),
            price = totalPrice.value,
            paymentStatus = true
        )

        Log.d(DataProvider.TAG, "New appointment: $appointment")

        newAppointmentController.saveNewAppointment(appointment)
    }

}
