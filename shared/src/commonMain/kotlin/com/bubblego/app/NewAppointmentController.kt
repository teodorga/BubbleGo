package com.bubblego.app

import com.bubblego.app.data.Appointment
import com.bubblego.app.utils.DataProvider

class NewAppointmentController {

    fun saveNewAppointment(appointment: Appointment) {
        println("${DataProvider.TAG} - Received appointment: $appointment")
    }
}