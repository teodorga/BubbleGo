package com.bubblego.app.network

import com.bubblego.app.data.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

object NetworkProvider {

    private const val BASE_URL = "http://192.168.50.48:8000/bubblego"

    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
            })
        }
    }

    suspend fun getServices(): NetworkResponse<List<Service>> {
        return try {
            val response: HttpResponse = client.get("$BASE_URL/services")
            if (response.status.value == 200) {
                val servicesResponse: ServicesResponse = response.body()
                NetworkResponse.Success(servicesResponse.data)
            } else {
                NetworkResponse.Error(response.status.toString())
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.message.toString())
        }
    }

    suspend fun getAppointments(): NetworkResponse<List<Appointment>> {
        return try {
            val response: HttpResponse = client.get("$BASE_URL/appointments")
            if (response.status.value == 200) {
                val servicesResponse: AppointmentsResponse = response.body()
                NetworkResponse.Success(servicesResponse.data)
            } else {
                NetworkResponse.Error(response.status.toString())
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.message.toString())
        }
    }

    suspend fun saveNewAppointment(appointment: Appointment): NetworkResponse<String> {
        val response: HttpResponse = client.post("$BASE_URL/appointments") {
            contentType(ContentType.Application.Json)
            setBody(appointment)
        }
        return if (response.status.value == 201) {
            val servicesResponse: PostResponse = response.body()
            NetworkResponse.Success(servicesResponse.message)
        } else {
            NetworkResponse.Error(response.status.toString())
        }
    }
}