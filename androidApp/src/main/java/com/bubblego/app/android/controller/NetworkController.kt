package com.bubblego.app.android.controller

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.bubblego.app.android.controller.models.UiService
import com.bubblego.app.android.ui.activities.data.DataProvider
import com.bubblego.app.data.Appointment
import com.bubblego.app.network.NetworkProvider
import com.bubblego.app.network.NetworkResponse

class NetworkController {

    suspend fun getServices(): NetworkResponse<List<UiService>> {
        Log.d(DataProvider.TAG, "Network call initiated getServices()")
        with(NetworkProvider.getServices()) {
            if (this is NetworkResponse.Success) {
                Log.d(DataProvider.TAG, "Server response: 200 OK")
                return NetworkResponse.Success(this.data!!.map {
                    UiService(
                        it.id,
                        mutableStateOf(it.checkbox_status),
                        it.service_title,
                        it.price
                    )
                })
            } else {
                Log.e(DataProvider.TAG, "Server error: ${this.message!!})")
                return NetworkResponse.Error(this.message!!)
            }
        }
    }

    suspend fun getAppointments(): NetworkResponse<List<Appointment>> {
        Log.d(DataProvider.TAG, "Network call initiated getAppointments()")
        with(NetworkProvider.getAppointments()) {
            if (this is NetworkResponse.Success) {
                Log.d(DataProvider.TAG, "Server response: 200 OK")
                return NetworkResponse.Success(this.data!!.map {
                    Appointment(
                        id = it.id,
                        services_list = it.services_list,
                        location = it.location,
                        date = it.date,
                        time = it.time,
                        details = it.details,
                        final_price = it.final_price,
                        payment_status = it.payment_status
                    )
                })
            } else {
                Log.e(DataProvider.TAG, "Server error: ${this.message!!})")
                return NetworkResponse.Error(this.message!!)
            }
        }
    }

    suspend fun saveNewAppointment(
        services: List<Int>,
        location: Pair<Double, Double>,
        dateAndTime: Pair<String, String>,
        details: String,
        price: Int,
        paymentStatus: Boolean
    ) {
        val appointment = Appointment(
            id = 0,
            services_list = services,
            location = "${location.first} ${location.second}",
            date = dateAndTime.first,
            time = dateAndTime.second,
            details = details,
            final_price = price.toString(),
            payment_status = paymentStatus
        )

        Log.d(DataProvider.TAG, "New appointment: $appointment")

        with(NetworkProvider.saveNewAppointment(appointment)) {
            if (this is NetworkResponse.Success) {
                Log.d(DataProvider.TAG, "Server response: ${this.data}")
            } else {
                Log.e(DataProvider.TAG, "Server error: ${this.message!!}")
            }
        }
    }
}