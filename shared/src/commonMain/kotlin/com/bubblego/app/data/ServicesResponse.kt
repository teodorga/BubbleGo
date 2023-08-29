package com.bubblego.app.data

import kotlinx.serialization.Serializable

@Serializable
data class ServicesResponse(
    val data: List<Service>
)