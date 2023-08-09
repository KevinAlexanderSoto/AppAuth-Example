package com.skgtecnologia.helios.authenticationmodule.di

import android.content.Intent
import android.net.Uri
import com.skgtecnologia.helios.authenticationmodule.presentation.LogInInformationViewModel
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logInModule = module {
    viewModel {
        LogInInformationViewModel()
    }

    single<Intent> {
        val serviceConfig = AuthorizationServiceConfiguration(
            Uri.parse("https://keycloak.sitbogota.com/auth/realms/smm-qa-env/protocol/openid-connect/auth"), // authorization endpoint
            Uri.parse(""), // token endpoint
        )

        val clientId = "smm-qa-test"
        val redirectUri = Uri.parse("com.skgtecnologia.helios.authenticationmodule:/oauth2redirect")
        val builder = AuthorizationRequest.Builder(
            serviceConfig,
            clientId,
            ResponseTypeValues.CODE,
            redirectUri,
        )
        builder.setScopes("openid profile email")

        val authRequest = builder.build()

        val authService = AuthorizationService(get())
        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        authIntent
    }
}
