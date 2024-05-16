package com.example.effectivemobiletestapp.repository.api.utils

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


object OkHttpBuilder {
    fun build() = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .callTimeout(CALL_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .build()


    private const val CALL_TIMEOUT_IN_SECONDS = 5L
    private const val CONNECT_TIMEOUT_IN_SECONDS = 8L
}