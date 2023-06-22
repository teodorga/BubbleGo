package com.bubblego.app.data

data class Appointment(
    val services: List<String>,
    val location: Pair<Double, Double>,
    val details: List<String>,
    val price: Int,
    val paymentStatus: Boolean
)
