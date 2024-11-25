package com.harunbekcan.kmpmoviedbapp.android

import android.app.Application
import com.harunbekcan.kmpmoviedbapp.android.di.appModule
import com.harunbekcan.kmpmoviedbapp.di.getSharedModules
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}