package com.example.saltest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SaltApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}