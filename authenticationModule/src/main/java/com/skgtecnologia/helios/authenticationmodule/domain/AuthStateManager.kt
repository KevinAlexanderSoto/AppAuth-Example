package com.skgtecnologia.helios.authenticationmodule.domain

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.auth0.android.jwt.JWT
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

    fun restoreState(): AuthState {
        val jsonString = context
            .getSharedPreferences(
                AuthenticationRequestIntentProvider.SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE,
            )
            .getString(AuthenticationRequestIntentProvider.AUTH_STATE, null)

        if (jsonString != null && !TextUtils.isEmpty(jsonString)) {
            try {
                authState = AuthState.jsonDeserialize(jsonString)

                if (!TextUtils.isEmpty(authState.idToken)) {
                    val jwt = JWT(authState.idToken!!)
                }
            } catch (_: JSONException) {
            }
        }
        return authState
    }
}
