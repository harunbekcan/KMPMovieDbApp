package com.harunbekcan.kmpmoviedbapp.util

import kotlinx.coroutines.CoroutineDispatcher

internal interface Dispatcher {
    val io: CoroutineDispatcher
}

internal expect fun provideDispatcher(): Dispatcher