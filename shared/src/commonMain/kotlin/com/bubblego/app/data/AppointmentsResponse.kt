package com.bubblego.app.data

import kotlinx.serialization.Serializable

@Serializable
data class AppointmentsResponse(
    val data: List<Appointment>
)