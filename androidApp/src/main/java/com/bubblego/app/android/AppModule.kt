package com.bubblego.app.android

import com.bubblego.app.NewAppointmentController
import com.bubblego.app.android.ui.activities.data.DataProvider
import com.bubblego.app.android.ui.models.UiService
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
}