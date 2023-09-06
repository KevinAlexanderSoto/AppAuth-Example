package com.skgtecnologia.helios.authenticationexample.network

import org.koin.dsl.module

val networkModule = module {
    factory<NetworkCallHandler> {
        NetworkCallHandler(get(), get(), get())
    }
}
