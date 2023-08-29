package com.bubblego.app.android.ui.activities

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bubblego.app.android.controller.NetworkController
import com.bubblego.app.android.controller.models.UiService
import com.bubblego.app.android.ui.activities.data.DataProvider
import com.bubblego.app.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewAppointmentViewModel @Inject constructor(
    private val networkController: NetworkController
) : ViewModel() {

    var selectedServices: MutableState<List<UiService>> = mutableStateOf(emptyList())
    var bookedTimes: MutableState<List<Pair<String, String>>> = mutableStateOf(emptyList())
    val location: MutableState<Pair<Double, Double>> = mutableStateOf(Pair(0.0, 0.0))
    var totalPrice: MutableState<Int> = mutableStateOf(0)
    var selectedDate: MutableState<Long> = mutableStateOf(0)
    var selectedTime: MutableState<String> = mutableStateOf("0")

    val networkResponse: MutableState<String> = mutableStateOf("Loading..")
    var locationTextHolder: MutableState<String> =
        mutableStateOf("Place the marker on the map accordingly")

    init {
        Log.d(DataProvider.TAG, "NewAppointmentViewMode() init")

        viewModelScope.launch {
            with(networkController.getServices()) {
                if (this is NetworkResponse.Success) {
                    selectedServices.value = this.data!!
                    networkResponse.value = "True"
                } else {
                    networkResponse.value = "Network error, failed to fetch data."
                }
            }

            with(networkController.getAppointments()) {
                if (this is NetworkResponse.Success) {
                    bookedTimes.value =
                        this.data!!.map {
                            Pair(it.date, it.time)
                        }
                }
            }
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
        viewModelScope.launch {
            networkController.saveNewAppointment(
                services = selectedServices.value.filter { it.checkboxStatus.value }.map {
                    it.id
                },
                location = location.value,
                details = LocalDate.now().toString(),
                dateAndTime = Pair(
                    SimpleDateFormat(
                        "dd.MM.yyyy",
                        Locale.getDefault()
                    ).format(Date(selectedDate.value)).toString(),
                    selectedTime.value
                ),
                price = totalPrice.value,
                paymentStatus = true
            )
        }
    }

    fun getGeocoderAddress(context: Context, lat: Double, long: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val geocoder = Geocoder(context, Locale.getDefault())
            val address = geocoder.getFromLocation(
                lat,
                long,
                1,
            )
            locationTextHolder.value = address!![0].getAddressLine(0)
        }
    }

}
