package com.skgtecnologia.helios.authenticationmodule.domain

import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService

class AuthenticationTokenExchange(
    private val authorizationService: AuthorizationService,
    private val authStateManager: AuthStateManager,
) {
    fun requestToken(
        value: Intent,
        onTokenReceived: () -> Unit,
    ) {
        val authorizationResponse: AuthorizationResponse? =
            AuthorizationResponse.fromIntent(value)
        val error = AuthorizationException.fromIntent(value)

        authStateManager.persistState(AuthState(authorizationResponse, error))
        val currentAuthenticationState = AuthState(authorizationResponse, error)
        val tokenExchangeRequest = authorizationResponse?.createTokenExchangeRequest()

        if (tokenExchangeRequest != null) {
            authorizationService.performTokenRequest(tokenExchangeRequest) { response, exception ->
                if (exception != null) {
                    // Handle exception
                } else {
                    if (response != null) {
                        currentAuthenticationState.update(response, exception)
                        authStateManager.persistState(currentAuthenticationState)
                        onTokenReceived()
                    }
                }
            }
        }
    }
}
