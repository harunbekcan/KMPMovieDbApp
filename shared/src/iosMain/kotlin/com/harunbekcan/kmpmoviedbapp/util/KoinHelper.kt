package com.harunbekcan.kmpmoviedbapp.util

import com.harunbekcan.kmpmoviedbapp.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules((getSharedModules()))
    }
}