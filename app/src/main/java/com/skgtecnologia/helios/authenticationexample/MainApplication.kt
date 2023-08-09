package com.skgtecnologia.helios.authenticationexample

import android.app.Application
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
            modules(logInModule)
        }
    }
}
