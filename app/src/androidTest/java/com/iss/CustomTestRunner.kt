package com.iss

import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.iss.di.App

class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): App {
        return super.newApplication(cl, TestApplication::class.java.name, context) as App
    }
}