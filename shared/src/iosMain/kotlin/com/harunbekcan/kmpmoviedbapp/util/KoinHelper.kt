package com.harunbekcan.kmpmoviedbapp.util

import com.harunbekcan.kmpmoviedbapp.di.getAppModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules((getAppModules()))
    }
}