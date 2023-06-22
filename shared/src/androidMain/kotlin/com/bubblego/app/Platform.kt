package com.bubblego.app

import android.app.Application

class AndroidPlatform : Platform, Application() {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()