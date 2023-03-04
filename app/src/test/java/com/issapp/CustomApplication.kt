package com.issapp

import androidx.multidex.MultiDexApplication
import com.issapp.di.DaggerMyComponent

class CustomApplication : MultiDexApplication() {

    fun applicationInjector(): IssComponent {
        return  DaggerMyComponent.create()
    }
}