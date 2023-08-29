package com.bubblego.app.android.ui.activities

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bubblego.app.android.controller.NetworkController
import com.bubblego.app.android.ui.activities.data.DataProvider
import com.bubblego.app.data.Appointment
import com.bubblego.app.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFlowViewModel @Inject constructor(
    private val networkController: NetworkController
): ViewModel() {

    var appointments: MutableState<List<Appointment>> = mutableStateOf(emptyList())
    val networkResponse: MutableState<String> = mutableStateOf("Loading..")

    init {
        Log.d(DataProvider.TAG, "NewAppointmentViewMode() init")

        viewModelScope.launch {
            with(networkController.getAppointments()) {
                if (this is NetworkResponse.Success) {
                    appointments.value = this.data!!
                    networkResponse.value = "True"
                } else {
                    networkResponse.value = "Network error, failed to fetch data."
                }
            }
        }
    }
}