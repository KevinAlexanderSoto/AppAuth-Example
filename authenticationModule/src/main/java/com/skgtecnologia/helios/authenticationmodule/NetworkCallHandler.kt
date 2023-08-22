package com.skgtecnologia.helios.authenticationmodule

import com.skgtecnologia.helios.authenticationmodule.domain.AuthStateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationService
import java.net.UnknownHostException

class NetworkCallHandler(
    private val authStateManager: AuthStateManager,
    private val authorizationService: AuthorizationService,
) {
    fun <T> makeNetworkCall(
        call: suspend () -> T,
    ) = flow<Boolean> {
        emit(true) // Emit loading status
        try {
            GlobalScope.launch { // TODO: Change with a viewModel scope
                val authState = getCurrentAuthenticationState()
                val tokenRefresh = async(Dispatchers.IO) {
                    authState.performActionWithFreshTokens(
                        authorizationService,
                    ) { accessToken, idToken, exception ->
                        // TODO: Add an interceptor and update the token on every request, and set the interceptor to the API
                    }
                }
                // TODO: After refresh the token, make the network call
                tokenRefresh.await()
                call()
            }
        } catch (e: UnknownHostException) {
        }
    }

    private fun getCurrentAuthenticationState() = authStateManager.restoreState()
}
