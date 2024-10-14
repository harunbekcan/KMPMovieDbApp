package com.harunbekcan.kmpmoviedbapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform