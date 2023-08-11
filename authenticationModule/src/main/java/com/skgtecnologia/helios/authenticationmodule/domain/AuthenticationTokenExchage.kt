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

    private var _authState =
        MutableStateFlow<AuthState>(AuthState())
    val authState: StateFlow<AuthState>
        get() = _authState.asStateFlow()

    fun requestToken(
        value: Intent,
    ) {
        val authorizationResponse: AuthorizationResponse? =
            AuthorizationResponse.fromIntent(value)
        val error = AuthorizationException.fromIntent(value)

        authStateManager.persistState(AuthState(authorizationResponse, error))
        _authState.value = AuthState(authorizationResponse, error)
        val tokenExchangeRequest = authorizationResponse?.createTokenExchangeRequest()

        if (tokenExchangeRequest != null) {
            authorizationService.performTokenRequest(tokenExchangeRequest) { response, exception ->
                if (exception != null) {
                    _authState.update {
                        AuthState()
                    }
                } else {
                    if (response != null) {
                        _authState.value.update(response, exception)
                        authStateManager.persistState(_authState.value)
                    }
                }
            }
        }
    }
}
