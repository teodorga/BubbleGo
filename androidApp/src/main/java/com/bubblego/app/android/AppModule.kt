package com.bubblego.app.android

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.bubblego.app.NewAppointmentController
import com.bubblego.app.android.controller.NetworkController
import com.bubblego.app.android.ui.activities.data.DataProvider
import com.bubblego.app.android.controller.models.UiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideServices(): List<UiService> {
        return DataProvider.servicesList
    }

    @Provides
    fun provideNewAppointmentController(): NewAppointmentController {
        return NewAppointmentController()
    }

    @Provides
    fun provideNetworkController(): NetworkController {
        return NetworkController()
    }
}