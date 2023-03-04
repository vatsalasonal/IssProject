package com.issapp.di

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class IssApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        issComponent = DaggerIssComponent.builder().appModule(AppModule()).build()
    }

    companion object {
        lateinit var issComponent: IssComponent
    }
}
