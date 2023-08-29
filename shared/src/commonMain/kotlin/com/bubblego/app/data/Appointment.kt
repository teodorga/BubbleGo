package com.bubblego.app.data

@kotlinx.serialization.Serializable
data class Appointment(
    val id: Int,
    val services_list: List<Int>,
    val location: String,
    val date: String,
    val time: String,
    val details: String,
    val final_price: String,
    val payment_status: Boolean
)
