package com.bubblego.app.data

import kotlinx.serialization.Serializable


@Serializable
data class Service(
    val id: Int,
    val service_title: String,
    val checkbox_status: Boolean,
    val price: String
)
