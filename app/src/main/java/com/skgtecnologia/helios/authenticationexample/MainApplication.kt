package com.skgtecnologia.helios.authenticationexample

import android.app.Application
import com.skgtecnologia.helios.authenticationexample.api.DemoApiModule
import com.skgtecnologia.helios.authenticationexample.network.networkModule
import com.skgtecnologia.helios.authenticationmodule.di.logInModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(router, logInModule, networkModule, DemoApiModule)
        }
    }
}
