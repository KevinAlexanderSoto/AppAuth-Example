package com.skgtecnologia.helios.authenticationexample

import com.skgtecnologia.helios.authenticationmodule.domain.Router
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val router = module {

    single<Router> {
        AuthenticationRouterImpl(androidApplication())
    }
}
