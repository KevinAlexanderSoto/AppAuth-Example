package com.skgtecnologia.helios.authenticationmodule.di

import android.content.Intent
import com.skgtecnologia.helios.authenticationmodule.AuthenticationConfiguration
import com.skgtecnologia.helios.authenticationmodule.domain.AuthStateManager
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationRequestIntentProvider
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationTokenExchange
import com.skgtecnologia.helios.authenticationmodule.domain.BaseCustomTabFactory
import com.skgtecnologia.helios.authenticationmodule.domain.CustomTabFactory
import com.skgtecnologia.helios.authenticationmodule.presentation.LogInInformationViewModel
import net.openid.appauth.AppAuthConfiguration
import net.openid.appauth.AuthorizationService
import net.openid.appauth.browser.BrowserAllowList
import net.openid.appauth.browser.VersionedBrowserMatcher
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Remember to implement this module in the main application module declaration
 * **/
val logInModule = module {
    viewModel {
        LogInInformationViewModel()
    }

    single<AuthorizationService> {
        val appAuthConfiguration = AppAuthConfiguration.Builder()
            .setBrowserMatcher(
                BrowserAllowList(
                    VersionedBrowserMatcher.CHROME_CUSTOM_TAB,
                    VersionedBrowserMatcher.SAMSUNG_CUSTOM_TAB,
                ),
            )
            .build()
        AuthorizationService(get(), appAuthConfiguration)
    }

    single<Intent> {
        AuthenticationRequestIntentProvider(get(), get(), get()).getAuthenticationIntent()
    }
    single {
        AuthStateManager(androidApplication())
    }
    single {
        AuthenticationTokenExchange(get(), get())
    }

    factory<CustomTabFactory> {
        BaseCustomTabFactory(get())
    }
    factory<AuthenticationConfiguration> {
        AuthenticationConfiguration {}
    }
}
