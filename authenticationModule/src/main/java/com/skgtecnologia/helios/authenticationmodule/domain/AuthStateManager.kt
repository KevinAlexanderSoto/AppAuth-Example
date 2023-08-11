package com.skgtecnologia.helios.authenticationmodule.domain

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.openid.appauth.AuthState
import org.json.JSONException

/**
 * An persistence mechanism for an {@link AuthState} instance.
 * This stores the instance in a shared preferences file, and provides thread-safe access and
 * mutation.
 */
class AuthStateManager(
    private val context: Application,
) {
    private var authState: AuthState = AuthState()
    fun persistState(authState: AuthState) {
        context.getSharedPreferences(
            AuthenticationRequestIntentProvider.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE,
        )
            .edit()
            .putString(
                AuthenticationRequestIntentProvider.AUTH_STATE,
                authState.jsonSerializeString(),
            )
            .apply()
    }

    private val _currentToken =
        MutableStateFlow<String>("mutableListOf()")
    val currentToken: StateFlow<String>
        get() = _currentToken.asStateFlow()

    fun restoreState(): AuthState {
        val jsonString = context
            .getSharedPreferences(
                AuthenticationRequestIntentProvider.SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE,
            )
            .getString(AuthenticationRequestIntentProvider.AUTH_STATE, null)

        if (!jsonString.isNullOrEmpty()) {
            try {
                authState = AuthState.jsonDeserialize(jsonString)
                _currentToken.value = authState.accessToken ?: "No found"
// TODO: Handle this part
                if (!TextUtils.isEmpty(authState.idToken)) {
                    val jwt = JWT(authState.idToken!!)
                }
            } catch (_: JSONException) {
            }
        }
        return authState
    }
}
