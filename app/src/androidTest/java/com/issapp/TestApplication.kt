package com.issapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        println("HEY IM UP TESTING APPLICATION COMPONENT ==========================+>")
        DaggerTestApplicationComponent.create().inject(this)
    }
}