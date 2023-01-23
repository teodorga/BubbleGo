package com.bubblego.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform