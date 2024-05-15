package com.example.effectivemobiletestapp

import android.app.Application
import com.example.effectivemobiletestapp.di.DaggerApplicationComponent


class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}