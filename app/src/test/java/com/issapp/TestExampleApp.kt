package com.issapp

import androidx.multidex.MultiDexApplication
import com.issapp.di.DaggerMyComponent

open class TestExampleApp : MultiDexApplication() {

    fun component(): IssComponent {
        return DaggerMyComponent.create()
    }
}