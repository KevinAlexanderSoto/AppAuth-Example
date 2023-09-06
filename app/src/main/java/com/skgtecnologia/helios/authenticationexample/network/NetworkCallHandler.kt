package com.skgtecnologia.helios.authenticationexample.network

import com.skgtecnologia.helios.authenticationmodule.domain.AuthStateManager
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationTokenExchange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationService
import java.net.UnknownHostException

class NetworkCallHandler(
    private val authStateManager: AuthStateManager,
    private val authorizationService: AuthorizationService,
    private val authenticationTokenExchange: AuthenticationTokenExchange,
) {
    fun <T> makeNetworkCall(
        call: suspend () -> T,
    ) = flow<Boolean> {
        emit(true) // Emit loading status
        try {
            CoroutineScope(Dispatchers.IO).launch { // TODO: Change with a viewModel scope
                val tokenRefresh = async(Dispatchers.IO) {
                    authenticationTokenExchange.refreshToken() {
                        val authState = getCurrentAuthenticationState()
                        authState.performActionWithFreshTokens(
                            authorizationService,
                        ) { accessToken, idToken, exception ->
                            ApiNetworkInterceptor.setCurrentToken(accessToken)
                        }
                    }
                }
                // TODO: Review the working scope of this part
                tokenRefresh.await()
                call.invoke()
            }
        } catch (e: UnknownHostException) {
            // TODO: handle the error
        }
    }

    private fun getCurrentAuthenticationState() = authStateManager.restoreState()
}
