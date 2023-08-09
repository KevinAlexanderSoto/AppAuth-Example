package com.skgtecnologia.helios.authenticationmodule.di

import android.content.Intent
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationRequestIntentProvider
import com.skgtecnologia.helios.authenticationmodule.presentation.LogInInformationViewModel
import net.openid.appauth.AuthorizationService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logInModule = module {
    viewModel {
        LogInInformationViewModel()
    }

    single {
        AuthorizationService(get())
    }

    single<Intent> {
        AuthenticationRequestIntentProvider(get()).getAuthenticationIntent()
    }
}
