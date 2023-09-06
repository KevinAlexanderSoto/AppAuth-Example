package com.skgtecnologia.helios.authenticationmodule.domain

import android.content.Intent
import kotlinx.coroutines.flow.update
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService

/**
 * This is a class called AuthenticationTokenExchange that is responsible for exchanging an authorization code for an access token. It takes in an AuthorizationService and an AuthStateManager as dependencies.
 *
 * The requestToken function takes in an Intent and a callback function onTokenReceived. It first retrieves the AuthorizationResponse and any error from the Intent and persists it in the AuthStateManager.
 * Then, it creates a TokenExchangeRequest from the AuthorizationResponse and sends it to the AuthorizationService to exchange for an access token.
 *
 * If there is an exception during the token exchange, it is handled accordingly. If there is a response, the current authentication state is updated with the new token and persisted in the AuthStateManager.
 * Finally, the onTokenReceived callback is called.
 *
 * Overall, this class facilitates the exchange of an authorization code for an access token and manages the authentication state of the user.
 * @author Kevin Penaranda
 * **/
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

    fun refreshToken(onTokenUpdated: () -> Unit) {
        val authState = authStateManager.restoreState()
        if (authState.needsTokenRefresh) {
            authorizationService.performTokenRequest(authState.createTokenRefreshRequest()) { response, exception ->
                if (exception != null) {
                    // TODO: Handle exception
                } else {
                    if (response != null) {
                        authState.update(response, exception)
                        authStateManager.persistState(authState)
                        onTokenUpdated.invoke()
                    }
                }
            }
        } else {
            onTokenUpdated.invoke()
        }
    }
}
